package JPA.Ejecutables;

import JPA.Cliente;
import JPA.Utilidades.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class HibernateEliminar {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Ingrese el id del cliente a eliminar:");
        Long id = s.nextLong();
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, id);

            if (cliente != null) {
                System.out.println("Cliente encontrado: " + cliente);
                em.remove(cliente);
                em.getTransaction().commit();
                System.out.println("Cliente eliminado correctamente.");
            } else {
                em.getTransaction().rollback();
                System.out.println("Cliente con id " + id + " no encontrado.");
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}