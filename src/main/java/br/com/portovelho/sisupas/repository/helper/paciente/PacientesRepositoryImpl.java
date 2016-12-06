package br.com.portovelho.sisupas.repository.helper.paciente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.portovelho.sisupas.model.Paciente;
import br.com.portovelho.sisupas.repository.filter.PacienteFiltro;
import br.com.portovelho.sisupas.repository.paginacao.PaginacaoUtil;

public class PacientesRepositoryImpl implements PacientesRepositoryQueries {
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Paciente> filtrar(PacienteFiltro filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Paciente.class);

		paginacaoUtil.preparar(criteria, pageable);

		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private void adicionarFiltro(PacienteFiltro filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNomeCompleto())) {
				criteria.add(Restrictions.ilike("nomeCompleto", filtro.getNomeCompleto().trim(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(filtro.getNomeDaMae())) {
				criteria.add(Restrictions.ilike("nomeDaMae", filtro.getNomeDaMae().trim(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(filtro.getCns())) {
				criteria.add(Restrictions.eq("cns", filtro.getCns().trim()));
			}
			if (!StringUtils.isEmpty(filtro.getCpf())) {
				criteria.add(Restrictions.eq("cpf", filtro.getCpf().trim()));
			}
			if (!StringUtils.isEmpty(filtro.getRg())) {
				criteria.add(Restrictions.eq("rg", filtro.getRg().trim()));
			}
			if (!StringUtils.isEmpty(filtro.getIdade())) {
				criteria.add(Restrictions.eq("idade", Integer.parseInt(filtro.getIdade().trim())));
			}
		}
	}

	private Long total(PacienteFiltro filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Paciente.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
}
