package br.com.portovelho.sisupas.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.portovelho.sisupas.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import br.com.portovelho.sisupas.thymeleaf.processor.MessageElementTagProcessor;
import br.com.portovelho.sisupas.thymeleaf.processor.OrderElementTagProcessor;

public class UpaDialect extends AbstractProcessorDialect {

	public UpaDialect() {
		super("SisUpas", "upa", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		return processadores;
	}
}
