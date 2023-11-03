package pt.upt.ei.lp.db;

import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
	private static final String PERSISTANCE_UNIT_NAME = "LibraryJPA";
	private static EntityManagerFactory factory;
	private static EntityManager emanager = null;
	
	public static void fill() {
		System.out.println("========");
		System.out.println("  FILL");
		System.out.println("========");
		EntityManager em = getEM();
		Query q = null;
		List<Aluno> alunos = null;
		List<UC> uc = null;
		List<Nota> notas = null;
		List<Professor> professores = null;
		List<Turma> turmas = null;
		
		em.getTransaction().begin();
		AlunoService as = new AlunoService(getEM());
		List<Aluno> alunoList = as.findAllAlunos();
		for (Aluno a : alunoList) {
			as.removeAluno(a.getId());
		}
		UCService us = new UCService(getEM());
		List<UC> ucList = us.findAllUC();
		for (UC a : ucList) {
			us.removeUC(a.getId());
		}
		ProfessorService ps = new ProfessorService(getEM());
		List<Professor> professorList = ps.findAllProfessores();
		for (Professor a : professorList) {
			ps.removeProfessor(a.getId());
		}
		TurmaService ts = new TurmaService(getEM());
		List<Turma> turmaList = ts.findAllTurmas();
		for (Turma t : turmaList) {
			ts.removeTurma(t.getId());
		}
		em.getTransaction().commit();
		System.out.println("Cleaned DB");
		System.out.println("------------------------");
		
		em.getTransaction().begin();
		UC uc1 = us.updateUC(0, "LP");
		UC uc2 = us.updateUC(0, "EA");
		UC uc3 = us.updateUC(0, "ER");
		UC uc4 = us.updateUC(0, "SO");
		UC uc5 = us.updateUC(0, "AED");
		
		Turma t1 = ts.updateTurma(0, 'A');
		Turma t2 = ts.updateTurma(0, 'B');
		
		Professor p1 = ps.updateProfessor(0, "Joaquim Torres", "jmst@uportu.pt");
		Professor p2 = ps.updateProfessor(0, "Natércia Durão", "natercia@uportu.pt");
		Professor p3 = ps.updateProfessor(0, "Maria João Ferreira", "mjoao@uportu.pt");
		Professor p4 = ps.updateProfessor(0, "Bruno Veloso", "brunov@uportu.pt");
		Professor p5 = ps.updateProfessor(0, "Isabel Seruca / Paula Morais", "iseruca@uportu.pt / pmorais@uportu.pt");
				
		uc1.getProfessores().add(p1);
		uc2.getProfessores().add(p2);
		uc3.getProfessores().add(p3);
		uc4.getProfessores().add(p4);
		uc5.getProfessores().add(p5);
		
		p1.getUCs().add(uc1);
		p2.getUCs().add(uc2);
		p3.getUCs().add(uc3);
		p4.getUCs().add(uc4);
		p5.getUCs().add(uc5);
		
		Nota n1 = new Nota(0, 17, uc1);
		Nota n2 = new Nota(0, 15, uc2);
		Nota n3 = new Nota(0, 16, uc2);
		Nota n4 = new Nota(0, 14, uc3);
		Nota n5 = new Nota(0, 17, uc4);
		Nota n6 = new Nota(0, 16, uc5);
		Nota n7 = new Nota(0, 15, uc5);
		
		Aluno a1 = as.updateAluno(0, "Hugo", 44382);
		Aluno a2 = as.updateAluno(0, "Miguel", 44799);
		Aluno a3 = as.updateAluno(0, "Cleusio", 43855);
				
		a1.getNotas().add(n1);
		a1.getNotas().add(n2);
		a1.getNotas().add(n3);
		a1.getNotas().add(n4);
		a1.getNotas().add(n5);
		a1.getNotas().add(n6);
		a1.getNotas().add(n7);
		
		a2.getNotas().add(n1);
		a2.getNotas().add(n2);
		a2.getNotas().add(n3);
		a2.getNotas().add(n4);
		a2.getNotas().add(n5);
		a2.getNotas().add(n6);
		a2.getNotas().add(n7);
		
		a3.getNotas().add(n1);
		a3.getNotas().add(n2);
		a3.getNotas().add(n3);
		a3.getNotas().add(n4);
		a3.getNotas().add(n5);
		a3.getNotas().add(n6);
		a3.getNotas().add(n7);
		
		t1.getAlunos().add(a1);
		t2.getAlunos().add(a2);
		t2.getAlunos().add(a3);
		
		em.getTransaction().commit();
		
		turmas = ts.findAllTurmas();
		System.out.println("------------------------");
		System.out.println("Turmas table");
		for (Turma t : turmas) {
			System.out.println(t);
		}
		
		alunos = as.findAllAlunos();
		System.out.println("------------------------");
		System.out.println("Alunos table");
		for (Aluno a : alunos) {
			System.out.println(a);
		}
		
		professores = ps.findAllProfessores();
		System.out.println("------------------------");
		System.out.println("Professores table");
		for (Professor p : professores) {
			System.out.println(p);
		}
		
		uc = us.findAllUC();
		System.out.println("------------------------");
		System.out.println("UC table");
		for (UC u : uc) {
			System.out.println(u);
		}
		System.out.println("------------------------");
		System.out.println("\n\nFinished!!!");
	}
	
	public static EntityManager getEM() {
		if(emanager == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
			emanager = factory.createEntityManager();
		}
		return emanager;
	}
	
	public static void main(String[] args) {
		fill();
	}

}
