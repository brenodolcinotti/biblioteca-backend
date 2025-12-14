const API = "http://localhost:4567";

// CLIENTE
function criarCliente() {
    fetch(`${API}/clientes`, {
        method: "POST",
        mode: "no-cors",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            nome: clienteNome.value
        })
    }).then(() => listarClientes());
}

function listarClientes() {
    fetch(`${API}/clientes`)
        .then(res => res.json())
        .then(clientes => {
            listaClientes.innerHTML = "";
            clientes.forEach(c => {
                listaClientes.innerHTML += `
                    <li>
                        ID ${c.id} - ${c.nome}
                        <button onclick="editarCliente(${c.id}, '${c.nome}')">Editar</button>
                        <button onclick="excluirCliente(${c.id})">Excluir</button>
                    </li>`;
            });
        });
}

function editarCliente(id, nomeAtual) {
    const novoNome = prompt("Digite o novo nome:", nomeAtual);
    if (novoNome && novoNome !== nomeAtual) {
        fetch(`${API}/clientes/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ nome: novoNome })
        }).then(() => listarClientes());
    }
}

function excluirCliente(id) {
    if (confirm(`Tem certeza que deseja excluir o cliente ID ${id}?`)) {
        fetch(`${API}/clientes/${id}`, {
            method: "DELETE"
        }).then(() => listarClientes());
    }
}

// LIVRO
function criarLivro() {
    fetch(`${API}/livros`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            nome: livroNome.value,
            autor: livroAutor.value
        })
    }).then(() => listarLivros());
}

function listarLivros() {
    fetch(`${API}/livros`)
        .then(res => res.json())
        .then(livros => {
            listaLivros.innerHTML = "";
            livros.forEach(l => {
                listaLivros.innerHTML += `<li>ID ${l.id} - ${l.nome}</li>`;
            });
        });
}

// EMPRESTIMO
function criarEmprestimo() {
    fetch(`${API}/emprestimos`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            idCliente: Number(empClienteId.value),
            idLivro: Number(empLivroId.value)
        })
    }).then(() => listarEmprestimos());
}

function listarEmprestimos() {
    fetch(`${API}/emprestimos`)
        .then(res => res.json())
        .then(emps => {
            listaEmprestimos.innerHTML = "";
            emps.forEach(e => {
                listaEmprestimos.innerHTML +=
                    `<li>ID ${e.id} | Cliente ${e.idCliente} | Livro ${e.idLivro}</li>`;
            });
        });
}
