package de.pdbm.starter.business.messages.boundary.control;

import de.pdbm.starter.business.messages.entity.Brand;
import de.pdbm.starter.business.messages.service.BrandService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class BrandController implements Serializable {
    @Inject
    BrandService brandService;

    private Integer brandId;

    private String brandName;

    private List<Brand> brandList;

    private int currentPage = 1;

    private int pageSize = 10;

    private long totalRecords;

    private Brand selectedBrand;

    // Konstruktor
    public BrandController() {
    }

    public BrandController(Integer brandId, String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }

    // Paginierung-Methoden
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Brand> getBrandList() {
        if (brandList == null || brandList.isEmpty()) {
            loadBrandList();
        }
        return brandList;
    }

    public void loadBrandList() {
        this.brandList = brandService.findPaginated(currentPage, pageSize);
    }

    public void nextPage() {
        currentPage++;
        loadBrandList();
    }

    public void prevPage() {
        if (currentPage > 0) {
            currentPage--;
            loadBrandList();
        }
    }

    public void firstPage() {
        currentPage = 1;
        loadBrandList();
    }

    public void lastPage() {
        currentPage = getTotalPages();
        loadBrandList();
    }

    public long getTotalRecords() {
        totalRecords = brandService.getBrandCount();
        return totalRecords;
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) totalRecords / pageSize);
    }

    public void setPage(int page) {
        if (page >= 1 && page <= getTotalPages()) {
            currentPage = page;
            loadBrandList();
        }
    }

    public Integer[] getPageNumbers() {
        int startPage = Math.max(1, currentPage - 2);
        int endPage = Math.min(getTotalPages(), currentPage + 4);
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            pageNumbers.add(i);
        }
        return pageNumbers.toArray(new Integer[0]);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        currentPage = 1;
        loadBrandList();
    }

    public void goToPage() {
        if(currentPage < 1) {
            currentPage = 1;
            loadBrandList();
        } else if(currentPage > getTotalPages()) {
            currentPage = getTotalPages();
            loadBrandList();
        } else {
            loadBrandList();
        }
    }

    // Getter und Setter
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

    public Brand getSelectedBrand() {
        return selectedBrand;
    }

    // Detailseite
    public String showDetails(Brand selectedBrand) {
        this.selectedBrand = selectedBrand;
        return "brandDetail.xhtml?faces-redirect=true";
    }

    // Eintrag loeschen
    public void deleteBrand(Brand brand) {
        brandService.delete(brand);
        loadBrandList();
        getTotalRecords();
    }

    // Aktualisierung
    public String updateBrandRecord(Brand brand) {
        brandService.update(selectedBrand);
        return "brandTable.xhtml?faces-redirect=true";
    }

    // Fuer Button in Header
    private int clicks;

    public boolean isButtonDisplayed() {
        return clicks % 2 == 1;
    }

    public void incrementClicks() {
        clicks++;
    }
}
