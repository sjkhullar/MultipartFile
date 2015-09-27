package org.learn.repo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.lang.model.element.Element;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/file")
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//logger.info("Welcome home! The client locale is {}.", locale);		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);		
		String formattedDate = dateFormat.format(date);		
		model.addAttribute("serverTime", formattedDate );		
		return "home";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST, consumes = {"multipart/mixed"})
    public @ResponseBody String handleFileUpload(
    		@RequestParam(value = "textMessage",required=false) String message,
            @RequestParam(value = "pdfFile", required = false) MultipartFile pdfFile,
            @RequestParam(value = "jsonFile", required = false) MultipartFile jsonFile, 
            @RequestParam(value = "zipFile", required = false) MultipartFile zipFile, 
            @RequestParam(value = "imgFile", required = false) MultipartFile imageFile,            
            MultipartHttpServletRequest request, ModelAndView modelAndView){    
		
		//We have specified the individual file. We can proceed ahead to use if required
		//Here we are extracting the file names from request parameters
		List<String> requestKeys = new ArrayList<String>();
		List<String> originalFileName = new ArrayList<String>();
		request.getFileNames().forEachRemaining(requestKeys::add);		
		for(String multiPartFile : requestKeys) {
			originalFileName.add(request.getFile(multiPartFile).getOriginalFilename());
		}
		return "uploaded files :" + originalFileName.toString();
		//for web clients
		//modelAndView.addObject("files", uploadedFileList);
		//return "FileUpload";		
    }
}
