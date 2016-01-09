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
package br.com.appesca.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.appesca.model.Equipe;

@ApplicationScoped
public class EquipeRepository {

    @Inject
    private EntityManager em;

    public Equipe findById(Long id) {
        return em.find(Equipe.class, id);
    }
    
    public void save(Equipe usr){
    	if(usr.getId()==null){
    		em.persist(usr);
    	}else{
    		em.merge(usr);
    	}
    }
    
    @SuppressWarnings("unchecked")
	public List<Equipe> listAll(){
    	 Query query = em.createQuery("SELECT u FROM Equipe u");
    	 return (List<Equipe>) query.getResultList();
    }
    
    public Equipe findByLoginSenha(String login, String senha) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Equipe> criteria = cb.createQuery(Equipe.class);
        Root<Equipe> equipe = criteria.from(Equipe.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
        criteria.select(equipe).where(
        		cb.and(
        				cb.equal(equipe.get("login"), login),
        				cb.equal(equipe.get("senha"), senha)
        		)
        );
        try{
        	return em.createQuery(criteria).getSingleResult();
        }catch(javax.persistence.NoResultException nr){
        	return null;
        }
    }

}
