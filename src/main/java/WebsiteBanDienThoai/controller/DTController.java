package WebsiteBanDienThoai.controller;

import WebsiteBanDienThoai.entity.Product;
import WebsiteBanDienThoai.entity.Role;
import WebsiteBanDienThoai.entity.User;
import WebsiteBanDienThoai.service.CategoryService;
import WebsiteBanDienThoai.service.ProductService;
import WebsiteBanDienThoai.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DTController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;


    @GetMapping("product/view")
    public  String viewProducts(Model model){
//        model.addAttribute("listProducts",productService.getAllProducts());
        String keyword = null;
        return findPaginated(1,"name","asc","keyword",model);
    }



    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                @Param("keyword") String keyword, Model model) {
        int pageSize = 5;

        Page<Product> page = productService.findPaginated(pageNo, pageSize, sortField, sortDir, keyword);
        List<Product> listProduct = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("keyword",keyword);
        return "/admin/listProduct";
    }



    //product/addnew
    @GetMapping ("product/addnew")
    public String showNewProductForm (Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "/admin/addProduct";
    }
    //product/save
    @PostMapping("product/save")
    public String saveProduct (@ModelAttribute("product") Product product) {
// save product to database
        productService. saveProduct(product);
        return "redirect:/product/view";
    }


    @GetMapping("product/edit/{id}")
    public String editBookForm(@PathVariable("id") int id, Model model){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/admin/editProduct";
    }

    @PostMapping("product/edit/{id}")
    public String editBook(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("product", product);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "admin/editProduct";
        } else {
            productService.updateProduct(product);
            return "redirect:/product/view";
        }
    }


    @GetMapping("product/delete/{id}")
    public String deleteBook(@PathVariable("id") int id){
        productService.deleteProductById(id);
        return "redirect:/product/view";
    }


    ///////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("user/view")
    public  String viewUsers(Model model){
//        model.addAttribute("listProducts",productService.getAllProducts());
        return findPaginatedUser(1,"name","asc",model);
    }



    @GetMapping("/pageUser/{pageNo}")
    public String findPaginatedUser(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                 Model model) {
        int pageSize = 5;

        Page<User> page = userService.findPaginatedUser(pageNo, pageSize, sortField, sortDir);
        List<User> listUser = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listUser", listUser);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc")?"desc":"asc");
        return "/admin/listUser";
    }




    //product/addnew
    @GetMapping ("user/addUser")
    public String showNewUserForm (Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/admin/addUser";
    }
    //product/save
    @PostMapping("user/save")
    public String saveUser (@ModelAttribute("user") User user) {
// save product to database
        userService. saveUser(user);
        return "redirect:/user/view";
    }


    @GetMapping("user/edit/{id}")
    public String editUserForm(@PathVariable("id") int id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", userService.getAllRole());
        return "/admin/editUser";
    }

    @PostMapping("user/edit/{id}")
    public String editUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("roles", userService.getAllRole());
            return "admin/editUser";
        } else {
            userService.updateUser(user);
            return "redirect:/user/view";
        }
    }


    @GetMapping("user/delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userService.deleteUserById(id);
        return "redirect:/user/view";
    }

    @GetMapping("/store")
    public String store(Model model){
        List<Product> listProduct = productService.getAllProducts();

      model.addAttribute("listProduct", listProduct);
        return "/store";
    }

}
