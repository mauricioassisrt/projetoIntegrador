package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import modelo.AvaliadorSubmissao;
import util.DAOGenerico;

@FacesConverter("avaliadorSubmissaoConverter")
public class AvaliadorSubmissaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				DAOGenerico dao = new DAOGenerico();
				Object obj = dao.recuperaPorId(AvaliadorSubmissao.class, Long.parseLong(value));
				return obj;
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((AvaliadorSubmissao) object).getId());
		} else {
			return null;
		}
	}
}

