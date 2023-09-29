package com.latam.alura.tienda.prueba;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;

public class RegistroDeProducto {

	public static void main(String[] args) {
		registrarProducto();
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		ProductoDao productoDao = new ProductoDao(em);
		Producto producto = productoDao.consultaPorId(1L);
		System.out.println(producto.getNombre());
		
		List<Producto> productos = productoDao.consultarTodos();
		productos.forEach(prod -> System.out.println(prod.getDescripcion()));
		
		List<Producto> productos2 = productoDao.consultaPorNombre("Samsung");
		productos2.forEach(prod -> System.out.println(prod.getDescripcion()));
		
		List<Producto> productos3 = productoDao.consultaPorNombreDeCategoria("CELULARES");
		productos3.forEach(prod -> System.out.println(prod.getDescripcion()));
		
		BigDecimal precioPN = productoDao.consultarPrecioPorNombreDeProducto("Samsung");
		System.out.println(precioPN);
		
		
//		EntityManager em2 = JPAUtils.getEntityManager();
//		em2.getTransaction().begin();
//		em2.persist(celulares);//realiza persistencia
//		
//		celulares.setNombre("LIBROS");//cambiamos el nombre
//		
//		em2.flush();//sincronizamos en la db
//		em2.clear();//pasamos a detach
//		
//		celulares = em2.merge(celulares);//pasamos este valor con estado manage
//		celulares.setNombre("SOFTWARES");//modificamos
//		em2.flush();//volvemos a actualizar
//		em2.clear();//lo pasamos a detach y daria error si no volvemos a pasarlo a manage
//		celulares = em2.merge(celulares);//lo pasamos a manage
//		em2.remove(celulares);
//		em2.flush();
		
		
		
	}

	private static void registrarProducto() {
		Categoria celulares = new Categoria("CELULARES");
		Producto celular = new Producto("Samsung", "telefono usado", new BigDecimal("1000"), celulares);
		
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ProductoDao productoDao = new ProductoDao(em);
		
		transaction.begin();
		categoriaDao.guardar(celulares);
		productoDao.guardar(celular);
		transaction.commit();
		em.close();
	}

}
