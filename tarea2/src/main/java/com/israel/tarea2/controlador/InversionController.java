package com.israel.tarea2.controlador;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.israel.tarea2.modelo.InversionModelo;
import com.israel.tarea2.servicio.ClienteServicio;
import com.israel.tarea2.servicio.InversionServicio;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/inversiones")
public class InversionController {
	@Autowired
	private InversionServicio inversionServ;
	@Autowired ClienteServicio clienteServ;

    @Autowired
    private InversionServicio inversionServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/inversionform")
    public String mostrarFormulario(Model model) {
        model.addAttribute("nuevaInversion", new InversionModelo());
        model.addAttribute("listaClientes", clienteServicio.listar()); // para elegir cliente
        return "formularios/inversionForm";
    }


    @PostMapping("/guardarInversion")
    public String guardarInversion(@ModelAttribute("nuevaInversion") InversionModelo nuevaInversion) {
        // Determinar tasa de interés según el número de periodos
        BigDecimal tasa;
        switch (nuevaInversion.getNumeroPeriodosPorAnio()) {
            case 2: tasa = new BigDecimal("0.05"); break;  // Semestral
            case 4: tasa = new BigDecimal("0.07"); break;  // Trimestral
            case 12: tasa = new BigDecimal("0.11"); break; // Mensual
            default: tasa = BigDecimal.ZERO; break;
        }
        nuevaInversion.setTasaInteres(tasa);

        // Cálculo de monto final: A = P(1 + r/n)^(nt)
        BigDecimal P = nuevaInversion.getCapitalInicial();
        BigDecimal r = tasa;
        int n = nuevaInversion.getNumeroPeriodosPorAnio();
        int t = nuevaInversion.getTiempoAnos();

        BigDecimal base = BigDecimal.ONE.add(r.divide(new BigDecimal(n), 10, RoundingMode.HALF_UP));
        BigDecimal exponente = new BigDecimal(n * t);
        BigDecimal montoFinal = P.multiply(base.pow(exponente.intValue()));
        montoFinal = montoFinal.setScale(2, RoundingMode.HALF_UP);

        nuevaInversion.setMontoFinal(montoFinal);
        nuevaInversion.setInteresGanado(montoFinal.subtract(P));

        // Asignación de categoría
        if (P.compareTo(new BigDecimal("100")) >= 0 && P.compareTo(new BigDecimal("500")) <= 0 && n == 12) {
            nuevaInversion.setCategoria(3);
        } else if (P.compareTo(new BigDecimal("501")) >= 0 && P.compareTo(new BigDecimal("1000")) <= 0 && n == 12) {
            nuevaInversion.setCategoria(2);
        } else if (P.compareTo(new BigDecimal("1000")) > 0 && n == 12) {
            nuevaInversion.setCategoria(1);
        } else {
            nuevaInversion.setCategoria(0);
        }
        System.out.println("Capital inicial: " + nuevaInversion.getCapitalInicial());
        // Guardar inversión
        inversionServicio.insertar(nuevaInversion);
        return "redirect:/inversiones/lista";
    }



    @GetMapping("/lista")
    public String mostrarLista(Model model) {
        System.out.println("Accediendo a la tabla");
        List<InversionModelo> lista = inversionServicio.listar();
        model.addAttribute("listaInversiones", lista);
        return "tablas/inversionTable";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        inversionServicio.eliminar(id);
        return "redirect:/inversiones/lista";
    }
}

