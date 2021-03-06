/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.org.unesco.appesca.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import br.org.unesco.model.Formulario;
import br.org.unesco.model.Usuario;

@ApplicationScoped
public class FormularioRepository {

    @Inject
    private EntityManager em;

    public Formulario findById(Long id) {
        return em.find(Formulario.class, id);
    }
    

    public List<Formulario> findListByUsuario(Usuario usuario) {
    	
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Formulario> criteria = cb.createQuery(Formulario.class);
        Root<Formulario> formulario = criteria.from(Formulario.class);
        criteria.select(formulario).where(cb.equal(formulario.get("idUsuario"), usuario.getId()));
        return em.createQuery(criteria).getResultList();
        
//        return em.find(Formulario.class, id);
    }
    
    public void save(Formulario usr){
//    	em.getTransaction().begin();
    	if(usr.getId()==null){
    		em.persist(usr);
    	}else{
    		em.merge(usr);
    	}
//    	em.getTransaction().commit();
    }
    
    @SuppressWarnings("unchecked")
	public List<Formulario> listAll(){
    	 Query query = em.createQuery("SELECT u FROM Formulario u");
    	 return (List<Formulario>) query.getResultList();
    }

}
