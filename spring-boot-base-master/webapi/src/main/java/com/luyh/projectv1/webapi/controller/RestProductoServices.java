package com.luyh.projectv1.webapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.luyh.projectv1.model.Producto;


@RestController
@RequestMapping(value = "/api/v1.0")
public class RestProductoServices {
	
	/*@Autowired
	private ProductoServicesDao productoServicesDao;*/
	
	@Autowired
	TokenStore tokenStore;
	
	//------------------- Create Producto --------------------------------------------------------
	
	@RequestMapping(value    = "/productos",
					method   = RequestMethod.POST,
			        headers  = "Accept=application/json",
			        consumes = MediaType.APPLICATION_JSON_VALUE, 
			        produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<Void> crearProducto(@RequestHeader(value="Authorization") String Authorization,  @RequestBody(required = true) Producto producto, UriComponentsBuilder ucBuilder){
		String[] accessToken=Authorization.split(" ");
		String finalHeader=accessToken[1];
		OAuth2AccessToken token=tokenStore.readAccessToken(finalHeader);
		String tokenval=token.getValue();
		System.out.println("accessToken"+accessToken);
		System.out.println("finalHeader"+finalHeader);
		System.out.println("token"+token);
		System.out.println("tokenval"+tokenval);
		
		/*if (productoServicesDao.isProductExist(producto)){
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		productoServicesDao.createProduct(producto);*/
		
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucBuilder.path("/productos/{id}").buildAndExpand(producto.getIdProducto()).toUri());
		
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}
	
	@RequestMapping(value    = "/secure",
			method   = RequestMethod.POST,
	        headers  = "Accept=application/json",
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<Void> crearProducto1(@RequestBody(required = true) Producto producto,
			UriComponentsBuilder ucBuilder, Authentication auth) {
		/*String name=auth.getName();
		Object principal=auth.getPrincipal();
		
		System.out.println("name"+name);
		System.out.println("principal"+principal);
		
		if (productoServicesDao.isProductExist(producto)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		productoServicesDao.createProduct(producto);*/
		
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		HttpHeaders header = new HttpHeaders();
		if(auth==null){
		
		header.setLocation(ucBuilder.path("/productos/{id}").buildAndExpand(producto.getIdProducto()).toUri());
		}
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}
	
	
	@RequestMapping(value    = "/test",
			method   = RequestMethod.POST,
	        headers  = "Accept=application/json",
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<Void> testBypass(@RequestBody(required = true) Producto producto,
			UriComponentsBuilder ucBuilder, Authentication auth) {
		
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		HttpHeaders header = new HttpHeaders();
		if(auth==null){
		
		header.setLocation(ucBuilder.path("/test").buildAndExpand(producto.getIdProducto()).toUri());
		}
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}
	
	@RequestMapping(value    = "/abc",
			method   = RequestMethod.GET,
	        headers  = "Accept=application/json",
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<Void> abc(@RequestBody(required = true) Producto producto,
			UriComponentsBuilder ucBuilder, Authentication auth) {
		
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		HttpHeaders header = new HttpHeaders();
		if(auth==null){
		
		header.setLocation(ucBuilder.path("/abc").buildAndExpand(producto.getIdProducto()).toUri());
		}
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}
	
	//------------------- List Producto --------------------------------------------------------
	
	/*@RequestMapping(value    = "/productos",
					method   = RequestMethod.GET,
			        headers  = "Accept=application/json",
			        consumes = MediaType.APPLICATION_JSON_VALUE, 
			        produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<List<Producto>> listadoProductos(){
		
		List<Producto> productos = productoServicesDao.getAllProducts();
		
		if (productos.size() == 0){
			return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);	
	}*/
	
	//------------------- List Producto Paginacion --------------------------------------------------------
	
	/*@RequestMapping(value    = "/productos/paginacion",
					method   = RequestMethod.GET,
			        headers  = "Accept=application/json",
			        consumes = MediaType.APPLICATION_JSON_VALUE, 
			        produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<List<Producto>> listadoProductos(@RequestParam(value = "start", required = true) int start, 
			                                               @RequestParam(value = "size", required = true)  int size){
		
		if (start == 0 || size == 0){
			return new ResponseEntity<List<Producto>>(HttpStatus.BAD_REQUEST);
		}
		
		List<Producto> productos = productoServicesDao.getAllProducts();
		
		if (productos.size() == 0){
			return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND);
		}
		
		if (start + size > productos.size()){
			return new ResponseEntity<List<Producto>>(HttpStatus.PAYLOAD_TOO_LARGE);
		}
		
		return new ResponseEntity<List<Producto>>(productos.subList(start, start + size), HttpStatus.OK);	
	}*/
	
	//------------------- List Producto --------------------------------------------------------
	
	/*@RequestMapping(value    = "/productos/{nombre}",
					method   = RequestMethod.GET,
			        headers  = "Accept=application/json",
			        consumes = MediaType.APPLICATION_JSON_VALUE, 
			        produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<List<Producto>> listadoProductosPorNombre(@PathVariable("nombre") String nombre){
		
		if (nombre.equalsIgnoreCase("") || nombre == null){
			return new ResponseEntity<List<Producto>>(HttpStatus.NO_CONTENT);
		}
		
		List<Producto> productos = productoServicesDao.getProductsByName(nombre);
		
		if (productos.size() == 0){
			return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);		
	}*/
	
	//------------------- Info Producto --------------------------------------------------------
	
	/*@RequestMapping(value    = "/productos/{codigo}",
					method   = RequestMethod.GET,
			        headers  = "Accept=application/json",
			        consumes = MediaType.APPLICATION_JSON_VALUE, 
			        produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<Producto> informacionProducto(@PathVariable("codigo") String codigo){
		
		if (codigo.equalsIgnoreCase("") || codigo == null){
			return new ResponseEntity<Producto>(HttpStatus.NO_CONTENT);
		}
		
		Producto producto = (Producto) productoServicesDao.getInfoProduct(codigo);
		
		if (producto == null){
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);	
	}*/
	
	//------------------- Delete Producto --------------------------------------------------------
	
	/*@RequestMapping(value    = "/productos/{codigo}",
					method   = RequestMethod.DELETE,
			        headers  = "Accept=application/json",
			        consumes = MediaType.APPLICATION_JSON_VALUE, 
			        produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<Producto> eliminarProducto(@PathVariable("codigo") String codigo){
		
		if (codigo.equalsIgnoreCase("") || codigo == null){
			return new ResponseEntity<Producto>(HttpStatus.NO_CONTENT);
		}
		
		if (!productoServicesDao.isProductExist(codigo)){
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
		
		boolean exito = productoServicesDao.deleteProductByCode(codigo);
		
		if (!exito){
			return new ResponseEntity<Producto>(HttpStatus.NOT_MODIFIED);	
		}
		
		return new ResponseEntity<Producto>(HttpStatus.OK);
	}
*/
	//------------------- Update Producto --------------------------------------------------------
	
	/*@RequestMapping(value    = "/productos/{codigo}",
					method   = RequestMethod.PUT,
			        headers  = "Accept=application/json",
			        consumes = MediaType.APPLICATION_JSON_VALUE, 
			        produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<Producto> actualizarProducto(@PathVariable("codigo") String codigo, @RequestBody(required = true) Producto producto){
		
		if (!productoServicesDao.isProductExist(codigo)){
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
		
		productoServicesDao.updateProduct(producto);
		
		return new ResponseEntity<Producto>(producto, HttpStatus.NOT_FOUND);
	}*/
	
}
