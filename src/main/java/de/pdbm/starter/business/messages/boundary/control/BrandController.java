package de.pdbm.starter.business.messages.boundary.control;

import de.pdbm.starter.business.messages.entity.Brand;
import de.pdbm.starter.business.messages.service.BrandService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BrandController implements Serializable {
    private Integer brandId;
    private String brandName;

    private List<Brand> brandList;
    @Inject
    BrandService brandService;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }
@PostConstruct
    public void setBrandList() {
        this.brandList = brandService.getAllBrand();
    }

    private int clicks;
    public boolean isButtonDisplayed(){
        return clicks % 2 == 1;
    }

    public void incrementClicks(){
        clicks++;
    }

}
