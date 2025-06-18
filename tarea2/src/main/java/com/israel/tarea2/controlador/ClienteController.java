package com.israel.tarea2.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.israel.tarea2.modelo.ClienteModelo;
import com.israel.tarea2.servicio.ClienteServicio;

import jakarta.validation.Valid;
@Controller
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteSer;

    @GetMapping("/clienteForm")
    public String accesoFormulario(Model model) {
        System.out.println("Accediendo al formulario de cliente");
        model.addAttribute("nuevo", new ClienteModelo());
        return "formularios/clienteForm";
    }

    @GetMapping("/clienteTable")
    public String accesoTabla(Model model) {
        System.out.println("Accediendo a la tabla");
        List<ClienteModelo> resultado = clienteSer.listar();
        model.addAttribute("listaClientes", resultado);
        return "tablas/clienteTable";  // ✅ CORRECTO
    }

    @PostMapping("/guardarCliente")
    public String guardarNuevoUsuario(
        @Valid @ModelAttribute("nuevo") ClienteModelo nuevoCliente,
        BindingResult result,
        Model model) {

        if (result.hasErrors()) {
            return "formularios/clienteForm"; // Vuelve a mostrar el formulario si hay errores
        }

        clienteSer.insertar(nuevoCliente);
        return "redirect:/clientes/clienteTable";
    }


    @GetMapping("/eliminarCliente/{id}")
    public String eliminarCliente(@PathVariable("id") Long id) {
        clienteSer.eliminar(id);
        return "redirect:/clientes/clienteTable";  // ✅ redirección después de eliminar
    }
}
