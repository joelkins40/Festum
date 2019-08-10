/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.modelo_cliente;
import conexion.BD;
import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lavaexport
 */
public class controlador_cliente extends modelo_cliente {

    String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;

    public DefaultTableModel cargar_tabla_cliente(String valor) {

        String[] titulos = {"Id_Cliente", "Razon Social", "Direccion", "TIPO DE PRECIO"};
        String[] registros = new String[4];
        model = new DefaultTableModel(null, titulos);

        ResultSet rs = consultarcliente();
        try {
            while (rs.next()) {

                registros[0] = rs.getString(11);
                registros[1] = rs.getString(1);
                registros[2] = rs.getString(2);
              
                if ("E".equals(rs.getString(10))) {
                    registros[3] = "Especial";
                } else if ("G".equals(rs.getString(10))) {
                    registros[3] = "General";

                } else {
                    registros[3] = "";
                }

                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo_cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;

    }

    public void guardar_cliente(String vaccion, String id_cliente, String razon, String direccion, String n_e, String n_i, String colonia, String cp, String referencias, String telefono, String ciudad, String precio) {
        if (vaccion == "I") {
            inserta_cliente(id_cliente,razon, direccion, n_e, n_i, colonia, cp, referencias, telefono, ciudad, precio);
        }
        if (vaccion == "M") {
            modificar_cliente(id_cliente, razon, direccion, n_e, n_i, colonia, cp, referencias, telefono, ciudad, precio);
        }
    }

    public void eliminar_cliente(String id) {
        eliminar_cli(id);
    }

    public ResultSet consultarclient(String id) {
        ResultSet rs = consultarcli(id);
        return rs;

    }
     public ResultSet SELECTMAXID() {
        ResultSet rs = consultarMXi();
        return rs;

    }
}
