/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.modelo_categoria;
import Modelo.modelo_cliente;
import Modelo.modelo_user;
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
public class controlador_user extends modelo_user {

    String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;

    public DefaultTableModel cargar_tabla_user(String valor) {

        String[] titulos = {"Clave", "Nombre", "Estatus", "Tipo"};
        String[] registros = new String[4];
        model = new DefaultTableModel(null, titulos);

        ResultSet rs = consultarusuario();
        try {
            while (rs.next()) {

                registros[0] = rs.getString(2);
                registros[1] = rs.getString(3);
                registros[2] = rs.getString(4);
                if ("A".equals(rs.getString(6))) {
                    registros[3] = "Administrador";
                } else if ("U".equals(rs.getString(6))) {
                    registros[3] = "Usuario";
                } else {
                    registros[3] = "";
                }

                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlador_user.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;

    }

    public void guardar_cliente(String vaccion, String clave, String nombre, String contrasena, String estatus, String tipo) {
        if (vaccion == "I") {
            inserta_user(clave, nombre, contrasena, estatus, tipo);
        }
        if (vaccion == "M") {
            modificar_user(clave, nombre, contrasena, estatus, tipo);
        }
    }

    public void eliminar_cliente(String id) {
        eliminar_user(id);
    }

    public ResultSet consultaruser(String id) {
        ResultSet rs = consultar_user(id);
        return rs;

    }
      public ResultSet consultar() {
        ResultSet rs = consultarusuario();
        return rs;

    }
        public ResultSet consultar_usurio_nombre(String nombre) {
        ResultSet rs = consultar_user_nombre(nombre);
        return rs;

    }
}
