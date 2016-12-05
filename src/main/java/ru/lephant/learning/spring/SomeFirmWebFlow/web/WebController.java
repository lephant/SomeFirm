package ru.lephant.learning.spring.SomeFirmWebFlow.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainPage(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ModelAndView contactsPage(ModelAndView modelAndView) {
        modelAndView.setViewName("contacts");
        return modelAndView;
    }

    @RequestMapping(value = "/delivery", method = RequestMethod.GET)
    public ModelAndView deliveryPage(ModelAndView modelAndView) {
        modelAndView.setViewName("delivery");
        return modelAndView;
    }


    // Test

    /*@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainPage(ModelAndView modelAndView) {
        modelAndView.setViewName("test/test");
        return modelAndView;
    }

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<Workshop> testMethod() {
        Session session = sessionFactory.openSession();
        List<Workshop> list = session
                .createCriteria(Workshop.class)
                .list();

        for (User users: list) {
            Hibernate.initialize(users.getUsername());
        }

        session.close();
        return list;
    }*/

}
