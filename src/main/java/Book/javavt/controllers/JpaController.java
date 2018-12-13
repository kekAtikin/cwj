package Book.javavt.controllers;
import Book.javavt.title.BookAutor;
import Book.javavt.title.BookTitle;
import Book.javavt.service.BookAutorService;
import Book.javavt.service.BookTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class JpaController {

    private static final String INSTRUMENT = "jpa";
    private static final String TITLE = "JPA";

    @Autowired
    @Qualifier("bookAutorJpaService")
    private BookAutorService bookAutorService;
    @Autowired
    @Qualifier("bookTitleJpaService")
    private BookTitleService bookTitleService;

    @RequestMapping(value = "/"+INSTRUMENT+"", method = RequestMethod.GET)
    public String printJdbc(ModelMap model) {
        model.addAttribute("title", TITLE);
        model.addAttribute("instrument", INSTRUMENT);

        List<BookAutor> listBookAutor = bookAutorService.list();
        List<BookTitle> listBookTitle = bookTitleService.list();

        model.addAttribute("listBookTitle",listBookTitle);
        model.addAttribute("listBookAutor",listBookAutor);

        return "content";
    }

    //CRUD oparations with CarBrand entity
    @RequestMapping(value = "/"+INSTRUMENT+"/newAutor", method = RequestMethod.GET)
    public String addAutor(ModelMap model) {
        model.addAttribute("title", TITLE);
        model.addAttribute("action", "Add new");

        BookAutor bookAutor = new BookAutor();
        model.addAttribute("bookAutor", bookAutor);

        return "autorForm";
    }

    @RequestMapping(value = { "/"+INSTRUMENT+"/newAutor" }, method = RequestMethod.POST)
    public String saveAutor(BookAutor bookAutor) {

        bookAutorService.saveOrUpdate(bookAutor);
        return "redirect:/"+INSTRUMENT;
    }

    @RequestMapping(value = { "/"+INSTRUMENT+"/delete-brand/{idAutor}" }, method = RequestMethod.GET)
    public String deleteAutor(@PathVariable int idAutor) {
        bookAutorService.delete(idAutor);
        return "redirect:/"+INSTRUMENT;
    }

    @RequestMapping(value = {  "/"+INSTRUMENT+"/edit-brand/{idAutor}" }, method = RequestMethod.GET)
    public String editAutor(@PathVariable int idAutor, ModelMap model) {
        model.addAttribute("title", TITLE);
        model.addAttribute("action", "Edit");

        BookAutor bookAutor = bookAutorService.get(idAutor);
        model.addAttribute("bookAutor", bookAutor);
        model.addAttribute("edit", true);
        return "autorForm";
    }

    @RequestMapping(value = {  "/"+INSTRUMENT+"/edit-brand/{idAutor}" }, method = RequestMethod.POST)
    public String updateAutor(BookAutor bookAutor) {
        bookAutorService.saveOrUpdate(bookAutor);
        return "redirect:/"+INSTRUMENT;
    }

    //CRUD oparations with CarModel entity
    @RequestMapping(value = "/"+INSTRUMENT+"/newModel", method = RequestMethod.GET)
    public String addModel(ModelMap model) {
        model.addAttribute("title", TITLE);
        model.addAttribute("action", "Add new");

        List<BookAutor> listBookAutor = bookAutorService.list();
        System.out.println(listBookAutor);
        BookTitle bookTitle = new BookTitle();
        model.addAttribute("listBookAutor", listBookAutor);
        model.addAttribute("bookTitle", bookTitle);

        return "titleForm";
    }

    @RequestMapping(value = { "/"+INSTRUMENT+"/newModel" }, method = RequestMethod.POST)
    public String saveModel(BookTitle bookTitle) {

        int idAutor = bookTitle.getIdAutor();
        bookTitle.setBookAutor(bookAutorService.get(idAutor));
        bookTitleService.saveOrUpdate(bookTitle);
        return "redirect:/"+INSTRUMENT;
    }

    @RequestMapping(value = { "/"+INSTRUMENT+"/delete-model/{idTitle}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable int idTitle) {
        bookTitleService.delete(idTitle);
        return "redirect:/"+INSTRUMENT;
    }

    @RequestMapping(value = {  "/"+INSTRUMENT+"/edit-model/{idTitle}" }, method = RequestMethod.GET)
    public String editModel(@PathVariable int idTitle, ModelMap model) {
        model.addAttribute("title", TITLE);
        model.addAttribute("action", "Edit");

        BookTitle bookTitle = bookTitleService.get(idTitle);
        List<BookAutor> listBookAutor = bookAutorService.list();

        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("listBookAutor", listBookAutor);

        return "titleForm";
    }

    @RequestMapping(value = {  "/"+INSTRUMENT+"/edit-model/{idTitle}" }, method = RequestMethod.POST)
    public String updateModel(BookTitle bookTitle) {

        int idAutor = bookTitle.getIdAutor();
        bookTitle.setBookAutor(bookAutorService.get(idAutor));

        bookTitleService.saveOrUpdate(bookTitle);
        return "redirect:/"+INSTRUMENT;
    }

    @RequestMapping(value = {"/"+INSTRUMENT+"/pdfReport", "/"+INSTRUMENT+"/xlsxReport.xlsx"}, method = RequestMethod.GET)
    public ModelAndView downloadReport(@RequestParam("view") String view) {
        ModelAndView modelAndView = new ModelAndView();

        List<BookAutor> listBookAutor = bookAutorService.list();
        List<BookTitle> listBookTitle = bookTitleService.list();
        // return a view which will be resolved by a ResourceBundleViewResolver
        modelAndView.addObject("bookAutors", listBookAutor);
        modelAndView.addObject("bookTitles", listBookTitle);
        modelAndView.setViewName(view);

        return modelAndView;
    }
}
