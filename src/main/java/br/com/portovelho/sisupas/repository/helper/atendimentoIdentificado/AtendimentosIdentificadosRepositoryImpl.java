package br.com.portovelho.sisupas.repository.helper.atendimentoIdentificado;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import br.com.portovelho.sisupas.model.AtendimentoIdentificado;
import br.com.portovelho.sisupas.repository.filter.AtendimentoIdentificadoFiltro;
import br.com.portovelho.sisupas.repository.paginacao.PaginacaoUtil;

public class AtendimentosIdentificadosRepositoryImpl implements AtendimentosIdentificadosRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<AtendimentoIdentificado> filtrar(AtendimentoIdentificadoFiltro filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(AtendimentoIdentificado.class);

		paginacaoUtil.preparar(criteria, pageable);

		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private void adicionarFiltro(AtendimentoIdentificadoFiltro filtro, Criteria criteria) {
		/*if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			if (filtro.getMunicipio() != null) {
				if (filtro.getMunicipio().getNome() != null) {
					criteria.add(Restrictions.eq("municipio", filtro.getMunicipio()));
				}
			}
		}*/
	}

	private Long total(AtendimentoIdentificadoFiltro filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(AtendimentoIdentificado.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
}
