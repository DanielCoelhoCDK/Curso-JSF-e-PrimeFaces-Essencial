package com.algaworks.erp.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped  //Anotação do CDI - para que a instancia gerada pelo CDI dure todo ciclo de vida da aplicação
public class EntityManagerProducer {

	private EntityManagerFactory factory;
	
	public EntityManagerProducer() {
		this.factory = Persistence.createEntityManagerFactory("AlgaWorksPU");
	}
	
	@Produces // - Essa anotação vai dizer que o método createEntityManager é um método produtor de EntityManager
	@RequestScoped // Essa anotação diz  que o CDI vai gerenciar a instancia que o método criar dentro do escopo de requisição - Ou seja, a cada requisição é uma instância nova.
	public EntityManager createEntityManager() {
		return this.factory.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes/*Esse método é pra poder fechar a instancia quando o EntityManage for encerrado, e ele sabe que tem que chamar o método devido a anotação @Dispose*/ EntityManager manager) {
		manager.close();
	}
}