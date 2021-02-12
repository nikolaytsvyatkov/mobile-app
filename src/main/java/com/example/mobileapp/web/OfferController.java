package com.example.mobileapp.web;

import com.example.mobileapp.model.Offer;
import com.example.mobileapp.model.User;
import com.example.mobileapp.service.BrandService;
import com.example.mobileapp.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/offers")
@Slf4j
public class OfferController {

    private BrandService brandService;
    private OfferService offerService;

    @Autowired
    public OfferController(BrandService brandService, OfferService offerService) {
        this.brandService = brandService;
        this.offerService = offerService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("/add")
    public String addOffer(Model model,
                           HttpSession session,
                           RedirectAttributes redirectAttributes,
                           @RequestParam(required = false, name = "mode") String edit,
                           @RequestParam(required = false) Long offerId) {

        if (session.getAttribute("user") == null) {
            redirectAttributes.addAttribute("redirectUrl", "/offers/add");
            return "redirect:/auth/login";
        }


        if (edit == null) {
            if (!model.containsAttribute("offer")) {
                model.addAttribute("offer", new Offer());
            }
        } else {
            model.addAttribute("offer", this.offerService.getOfferById(offerId));
        }


        model.addAttribute("brands", this.brandService.getBrands());
        return "offer-add";
    }

    @PostMapping("/add")
    public String addNewOffer(@Valid @ModelAttribute("offer") Offer offer,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              HttpSession httpSession) {

        offer.setSeller((User) httpSession.getAttribute("user"));
        System.out.println();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offer", offer);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offer", bindingResult);
            return "redirect:add";
        }
        System.out.println();
        if (offer.getId() == null) {
            this.offerService.createOffer(offer);
        } else {
            this.offerService.updateOffer(offer);

        }


        return "redirect:all";
    }

    @GetMapping("/all")
    public String getAllOffers(Model model) {
        model.addAttribute("offers", this.offerService.getOffers());
        return "offers";
    }

    @GetMapping("/details/{offerId}")
    public String getDetails(@PathVariable Long offerId,
                             Model model) {

        model.addAttribute("offer", this.offerService.getOfferById(offerId));


        return "details";
    }

    @PostMapping(value = "/details/{offerId}", params = "offerId")
    public String updateOffer(@RequestParam Long offerId,
                              UriComponentsBuilder uriBuilder) {
        URI uri = uriBuilder.path("/offers/add")
                .query("mode=edit&offerId={id}").buildAndExpand(offerId).toUri();
        log.info(uri.toString());
        return "redirect:" + uri.toString();
    }

    @GetMapping(value = "/details/{offerId}/delete")
    public String delete(@PathVariable Long offerId) {
        this.offerService.deleteOffer(offerId);
        return "redirect:/оffers/all";
    }
}
