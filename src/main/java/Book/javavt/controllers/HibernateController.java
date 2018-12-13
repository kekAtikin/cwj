
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
public class HibernateController {

    private static final String INSTRUMENT = "hibernate";
    private static final String TITLE = "Hibernate";

    @Autowired
    @Qualifier("bookAutorHibernateService")
    private BookAutorService bookAutorService;
    @Autowired
    @Qualifier("bookTitleHibernateService")
    private BookTitleService bookTitleService;

    @RequestMapping(value = "/"+INSTRUMENT+"", method = RequestMethod.GET)
    public String printJdbc(ModelMap title) {
        title.addAttribute("title", TITLE);
        title.addAttribute("instrument", INSTRUMENT);

        List<BookAutor> listBookAutor = bookAutorService.list();
        List<BookTitle> listBookTitle = bookTitleService.list();

        title.addAttribute("listBookTitle",listBookTitle);
        title.addAttribute("listBookAutor",listBookAutor);

        return "content";
    }

    //CRUD oparations with CarBrand entity
    @RequestMapping(value = "/"+INSTRUMENT+"/newAutor", method = RequestMethod.GET)
    public String addAutor(ModelMap title) {
        title.addAttribute("title", TITLE);
        title.addAttribute("action", "Add new");

        BookAutor bookAutor = new BookAutor();
        title.addAttribute("bookAutor", bookAutor);

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
    public String editAutor(@PathVariable int idAutor, ModelMap title) {
        title.addAttribute("title", TITLE);
        title.addAttribute("action", "Edit");

        BookAutor bookAutor = bookAutorService.get(idAutor);
        title.addAttribute("bookAutor", bookAutor);
        title.addAttribute("edit", true);
        return "autorForm";
    }

    @RequestMapping(value = {  "/"+INSTRUMENT+"/edit-autor/{idAutor}" }, method = RequestMethod.POST)
    public String updateAutor(BookAutor bookAutor) {
        bookAutorService.saveOrUpdate(bookAutor);
        return "redirect:/"+INSTRUMENT;
    }

    //CRUD oparations with CarModel entity
    @RequestMapping(value = "/"+INSTRUMENT+"/newTitle", method = RequestMethod.GET)
    public String addTitle(ModelMap title) {
        title.addAttribute("title", TITLE);
        title.addAttribute("action", "Add new");

        List<BookAutor> listBookAutor = bookAutorService.list();
        System.out.println(listBookAutor);
        BookAutor bookAutor = new BookAutor();
        title.addAttribute("listBookAutor", listBookAutor);
        title.addAttribute("bookAutor", bookAutor);

        return "titleForm";
    }

    @RequestMapping(value = { "/"+INSTRUMENT+"/newTitle" }, method = RequestMethod.POST)
    public String saveTitle(BookTitle bookTitle) {

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

    @RequestMapping(value = {  "/"+INSTRUMENT+"/edit-model/{idTitlel}" }, method = RequestMethod.GET)
    public String editModel(@PathVariable int idTitle, ModelMap title) {
        title.addAttribute("title", TITLE);
        title.addAttribute("action", "Edit");

        BookTitle bookTitle = bookTitleService.get(idTitle);
        List<BookAutor> listBookAutor = bookAutorService.list();

        title.addAttribute("bookTitle", bookTitle);
        title.addAttribute("listBookAutor", listBookAutor);

        return "titleForm";
    }

    @RequestMapping(value = {  "/"+INSTRUMENT+"/edit-model/{idTitle}" }, method = RequestMethod.POST)
    public String updateTitle(BookTitle bookTitle) {

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
        modelAndView.addObject("bookAutor", listBookAutor);
        modelAndView.addObject("bookTitle", listBookTitle);
        modelAndView.setViewName(view);

        return modelAndView;
    }
}