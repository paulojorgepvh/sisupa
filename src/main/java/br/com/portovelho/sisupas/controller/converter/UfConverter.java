package br.com.portovelho.sisupas.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.portovelho.sisupas.model.UF;

public class UfConverter implements Converter<String, UF> {

	@Override
	public UF convert(String id) {
		if (!StringUtils.isEmpty(id)) {
			UF uf = new UF();
			uf.setId(Long.valueOf(id));
			return uf;
		}
		return null;
	}
}
