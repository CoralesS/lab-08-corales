// URLs de tu API
const API_PROYECTOS = 'api/proyectos';
const API_TAREAS = 'api/tareas';

// 1. CARGAR PROYECTOS
function cargarProyectos() {
    fetch(API_PROYECTOS)
        .then(response => response.json())
        .then(proyectos => {
            const select = document.getElementById('selectProyecto');
            select.innerHTML = '<option value="" disabled selected>Seleccione un Proyecto...</option>';

            proyectos.forEach(p => {
                const option = document.createElement('option');
                option.value = p.id;
                option.textContent = p.nombre;
                select.appendChild(option);
            });
        })
        .catch(err => console.error("Error cargando proyectos:", err));
}

// 2. CARGAR TAREAS
function cargarTareas() {
    fetch(API_TAREAS)
        .then(response => response.json())
        .then(tareas => {
            const contenedor = document.getElementById('lista-tareas');
            contenedor.innerHTML = '';

            if (tareas.length === 0) {
                contenedor.innerHTML = '<div class="alert alert-info">No hay tareas registradas.</div>';
                return;
            }

            tareas.forEach(t => {
                let bordeClase = '';
                let badgeColor = '';
                let textoEstado = t.nombreEstado || 'Desconocido';

                if (t.estadoId === 1) { bordeClase = 'estado-pendiente'; badgeColor = 'bg-danger'; }
                else if (t.estadoId === 2) { bordeClase = 'estado-progreso'; badgeColor = 'bg-warning text-dark'; }
                else if (t.estadoId === 3) { bordeClase = 'estado-finalizado'; badgeColor = 'bg-success'; }

                const html = `
                    <div class="card p-3 mb-2 ${bordeClase}">
                        <div class="d-flex justify-content-between align-items-center">
                            <h5 class="mb-0">${t.titulo}</h5>
                            <span class="badge ${badgeColor}">${textoEstado}</span>
                        </div>
                        <small class="text-muted">Proyecto: <strong>${t.nombreProyecto}</strong></small>
                        <p class="mt-2 mb-2">${t.descripcion || ''}</p>

                        <div class="btn-group btn-group-sm">
                            ${t.estadoId !== 1 ? `<button class="btn btn-outline-secondary" onclick="cambiarEstado(${t.id}, 1)">Pendiente</button>` : ''}
                            ${t.estadoId !== 2 ? `<button class="btn btn-outline-warning" onclick="cambiarEstado(${t.id}, 2)">En Progreso</button>` : ''}
                            ${t.estadoId !== 3 ? `<button class="btn btn-outline-success" onclick="cambiarEstado(${t.id}, 3)">Finalizar</button>` : ''}
                        </div>
                    </div>
                `;
                contenedor.innerHTML += html;
            });
        })
        .catch(err => console.error("Error cargando tareas:", err));
}

// 3. REGISTRAR PROYECTO
document.getElementById('form-proyecto').addEventListener('submit', function(e) {
    e.preventDefault();
    const data = {
        nombre: document.getElementById('nombreProyecto').value,
        descripcion: document.getElementById('descProyecto').value,
    };

    fetch(API_PROYECTOS, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then(res => res.json()).then(resp => {
        if(resp.exito) {
            alert("Proyecto creado");
            document.getElementById('form-proyecto').reset();
            cargarProyectos();
        } else {
            alert("Error al crear proyecto");
        }
    });
});

// 4. REGISTRAR TAREA
document.getElementById('form-tarea').addEventListener('submit', function(e) {
    e.preventDefault();
    const data = {
        proyectoId: parseInt(document.getElementById('selectProyecto').value),
        titulo: document.getElementById('tituloTarea').value,
        descripcion: document.getElementById('descTarea').value,
        estadoId: 1
    };

    fetch(API_TAREAS, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then(res => res.json()).then(resp => {
        if(resp.exito) {
            alert("Tarea agregada");
            document.getElementById('form-tarea').reset();
            cargarTareas();
        } else {
            alert("Error al crear tarea");
        }
    });
});

// 5. CAMBIAR ESTADO
window.cambiarEstado = function(idTarea, nuevoEstado) {
    const data = {
        id: idTarea,
        estadoId: nuevoEstado
    };

    fetch(API_TAREAS, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then(res => res.json()).then(resp => {
        if(resp.exito) {
            cargarTareas();
        } else {
            alert("No se pudo actualizar el estado");
        }
    });
};

// Inicializar
cargarProyectos();
cargarTareas();