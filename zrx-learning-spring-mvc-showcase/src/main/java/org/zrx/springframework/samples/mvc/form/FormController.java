package org.zrx.springframework.samples.mvc.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zrx.springframework.mvc.extensions.ajax.AjaxUtils;

import javax.validation.Valid;

/**
 * Function:    FormController
 * Author:      zhangrixiong
 * DateTime:    2016/8/22 17:54
 */
@Controller
@RequestMapping("/form")
@SessionAttributes("formBean")  // 从 session 中获取回话信息 formBean (反射获取)
public class FormController {

    // Invoked on every request

    /**
     *
     * @param request
     * @param model
     */
    @ModelAttribute
    public void ajaxAttribute(WebRequest request, Model model) {
        System.out.println("void ajaxAttribute(WebRequest request, Model model)  1");
        model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
    }

    // Invoked initially to create the "form" attribute
    // Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)

    /**
     *
     * @return
     */
    @ModelAttribute("formBean")
    public FormBean createFormBean() {
        System.out.println("FormBean createFormBean()  2");
        return new FormBean();
    }

    /**
     *
     */
    @RequestMapping(method=RequestMethod.GET)
    public void form() {
        System.out.println("void form() 3 ");
    }

    /**
     *
     * @param formBean
     * @param result
     * @param ajaxRequest
     * @param model
     * @param redirectAttrs
     * @return
     */
    @RequestMapping(method=RequestMethod.POST)
    public String processSubmit(@Valid FormBean formBean, BindingResult result,
                                @ModelAttribute("ajaxRequest") boolean ajaxRequest,
                                Model model, RedirectAttributes redirectAttrs) {

        System.out.println("String processSubmit(  )  ");

        if (result.hasErrors()) {
            return null;
        }
        // Typically you would save to a db and clear the "form" attribute from the session
        // via SessionStatus.setCompleted(). For the demo we leave it in the session.
        String message = "Form submitted successfully.  Bound " + formBean;
        // Success response handling
        if (ajaxRequest) {
            // prepare model for rendering success message in this request
            model.addAttribute("message", message);
            return null;
        } else {
            // store a success message for rendering on the next request after redirect
            // redirect back to the form to render the success message along with newly bound values
            redirectAttrs.addFlashAttribute("message", message);
            return "redirect:/form";
        }
    }
}
