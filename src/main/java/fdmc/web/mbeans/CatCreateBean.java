package fdmc.web.mbeans;

import fdmc.domain.models.binding.CatCreateBindingModel;
import fdmc.domain.models.service.CatServiceModel;
import fdmc.service.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class CatCreateBean {

    private CatCreateBindingModel catCreateBindingModel;

    private CatService catService;
    private ModelMapper modelMapper;

    public CatCreateBean(){
        this.catCreateBindingModel = new CatCreateBindingModel();
    }

    @Inject
    public CatCreateBean(CatService catService, ModelMapper modelMapper) {
        this();
        this.catService = catService;
        this.modelMapper = modelMapper;
    }

    public CatCreateBindingModel getCatCreateBindingModel() {
        return this.catCreateBindingModel;
    }

    public void setCatCreateBindingModel(CatCreateBindingModel catCreateBindingModel) {
        this.catCreateBindingModel = catCreateBindingModel;
    }

    public void  addCat() throws IOException {
        this.catService.saveCat(
                this.modelMapper.map(this.catCreateBindingModel, CatServiceModel.class));

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");

    }
}
