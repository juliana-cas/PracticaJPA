package JPA.Ejecutables;

import JPA.Cliente;
import JPA.Utilidades.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;

public class HibernateResultListWhere {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        EntityManager em = JpaUtil.getEntityManager();

        TypedQuery<Cliente> query = em.createQuery("select c from Cliente c where c.formaPago = :formaPago", Cliente.class);

        System.out.println("Ingrese una forma de pago: ");
        String pago = s.next();

        query.setParameter("formaPago", pago);

        List<Cliente> clientes = query.getResultList();

        System.out.println(clientes);

        em.close();
    }
}