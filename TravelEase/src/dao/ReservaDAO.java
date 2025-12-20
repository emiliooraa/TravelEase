package dao;

import bll.Reserva;
import java.util.List;

public interface ReservaDAO {

    List<Reserva> listarTodas();

    void confirmarReserva(String idReserva);

    void cancelarReserva(String idReserva);
}
