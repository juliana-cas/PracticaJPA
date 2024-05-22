package JPA.Ejecutables;

import JPA.Cliente;
import JPA.Utilidades.JpaUtil;
import jakarta.persistence.EntityManager;
import javax.swing.*;

public class HibernateEditar {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            String idStr = JOptionPane.showInputDialog("Ingrese el ID del cliente a editar:");
            Integer id = Integer.parseInt(idStr);

            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, id);

            if (cliente != null) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:", cliente.getNombre());
                String apellido = JOptionPane.showInputDialog("Ingrese el nuevo apellido:", cliente.getApellido());
                String pago = JOptionPane.showInputDialog("Ingrese la nueva forma de pago:", cliente.getFormaPago());

                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setFormaPago(pago);

                em.merge(cliente);
                em.getTransaction().commit();
                System.out.println("Cliente actualizado correctamente: " + cliente);
            } else {
                System.out.println("Cliente con ID " + id + " no encontrado.");
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}

