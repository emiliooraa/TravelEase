package dao;

import bll.Usuario;

public interface UsuarioDAO {
    Usuario login(String email, String contrasenia);
}
