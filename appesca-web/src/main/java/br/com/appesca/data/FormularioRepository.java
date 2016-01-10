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

import br.com.appesca.model.Formulario;

@ApplicationScoped
public class FormularioRepository {

    @Inject
    private EntityManager em;

    public Formulario findById(Long id) {
        return em.find(Formulario.class, id);
    }
    
    public void save(Formulario usr){
    	if(usr.getId()==null){
    		em.persist(usr);
    	}else{
    		em.merge(usr);
    	}
    }
    
    @SuppressWarnings("unchecked")
	public List<Formulario> listAll(){
    	 Query query = em.createQuery("SELECT u FROM Formulario u");
    	 return (List<Formulario>) query.getResultList();
    }

}
