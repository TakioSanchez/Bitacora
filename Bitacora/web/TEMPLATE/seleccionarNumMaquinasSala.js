function establecerNumMaxEquipo() {
    var id_sala = document.getElementById("id_sala");
    var sala_seleccionada = id_sala.selectedIndex;

    document.getElementById("num_maquinas").selectedIndex = sala_seleccionada;
    var num_maquinas = document.getElementById("num_maquinas").value;
    document.getElementById("num_equipo").max = num_maquinas;
    document.getElementById("num_equipo").min = 0;
}