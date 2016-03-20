package spring.core.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import spring.core.csv.loader.auditorium.AuditoriumLoader;
import spring.core.data.Auditorium;
import spring.core.data.FileBucket;
import spring.core.data.MultiFileBucket;
import spring.core.service.AuditoriumService;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileUploadController {

    private static String UPLOAD_LOCATION = "C:/temp/";

    @Autowired
    AuditoriumLoader auditoriumLoader;

    @Autowired
    FileValidator fileValidator;

    @Autowired
    MultiFileValidator multiFileValidator;

    @Autowired
    private AuditoriumService auditoriumService;

    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    @InitBinder("multiFileBucket")
    protected void initBinderMultiFileBucket(WebDataBinder binder) {
        binder.setValidator(multiFileValidator);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload(ModelMap model) {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        return "uploadfile";
    }

    @RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
    public String getSingleUploadPage(ModelMap model) {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        return "singleFileUploader";
    }

    @RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
    public String singleFileUpload(@Valid FileBucket fileBucket,
                                   BindingResult result, ModelMap model) throws IOException {

        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "singleFileUploader";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();

            List<Auditorium> auditoriums = auditoriumLoader.loadAuditoriumList(multipartFile.getInputStream());

            for (Auditorium auditorium : auditoriums) {
                auditoriumService.addAuditorium(auditorium);
            }

            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);

            model.addAttribute("auditoriums", auditoriumService.getAuditoriums());

            return "success";
        }
    }

    @RequestMapping(value = "/multiUpload", method = RequestMethod.GET)
    public String getMultiUploadPage(ModelMap model) {
        MultiFileBucket filesModel = new MultiFileBucket();
        model.addAttribute("multiFileBucket", filesModel);
        return "multiFileUploader";
    }

    @RequestMapping(value = "/multiUpload", method = RequestMethod.POST)
    public String multiFileUpload(@Valid MultiFileBucket multiFileBucket,
                                  BindingResult result, ModelMap model) throws IOException {

        if (result.hasErrors()) {
            System.out.println("validation errors in multi upload");
            return "multiFileUploader";
        } else {
            System.out.println("Fetching files");
            List<String> fileNames = new ArrayList<String>();

            for (FileBucket bucket : multiFileBucket.getFiles()) {
                List<Auditorium> auditoriums = auditoriumLoader.loadAuditoriumList(bucket.getFile().getInputStream());

                for (Auditorium auditorium : auditoriums) {
                    auditoriumService.addAuditorium(auditorium);
                }

                fileNames.add(bucket.getFile().getOriginalFilename());
            }

            model.addAttribute("fileNames", fileNames);
            model.addAttribute("auditoriums", auditoriumService.getAuditoriums());

            return "multiSuccess";
        }
    }

}