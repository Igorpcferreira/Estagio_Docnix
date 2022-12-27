//Consumo de API
const URL_BASE = "http://localhost:8080/Desafio6"

const URL_LISTAR_REGISTRO_FORMULARIO = URL_BASE + "/listar"

const URL_EXCLUIR_REGISTRO_FORMULARIO_BY_ID = URL_BASE + "/excluir"

const URL_EDITAR_REGISTRO_FORMULARIO_BY_ID = URL_BASE + "/editar"

const URL_INCLUIR_REGISTRO_FORMULARIO = URL_BASE + "/incluir"


async function incluirRegistroFormulario(formulario, url) {
    const requestOptions = {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formulario)
    }
    await fetch(url, requestOptions);
}

let idFormularioAserEditado;

async function editarRegistroFormulario(formulario) {
    await fetch(URL_EDITAR_REGISTRO_FORMULARIO_BY_ID, {
        method: 'POST',
        mode: "no-cors",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formulario)
    })
}

let registroFormularios;

async function listarTodosFormularios(url) {
    const requestOptions = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    };

    let response = await fetch(url, requestOptions);
    let data = await response.json();
    return data;
}


let formularioObjData = {}

function construirATabela() {
    listarTodosFormularios(URL_LISTAR_REGISTRO_FORMULARIO).then(function (result) {
        registroFormularios = result
        console.log(registroFormularios)
        adicionarDadosNaTabela(registroFormularios);
    });
}
construirATabela();

async function excluirRegistroFormulario(url, id){

    const requestOptions = {
        method: "GET",
    }
    console.log(id);
    await fetch(url + `?id=${id}`,requestOptions);
}

let nomeInput = document.getElementById('name');
let emailInput = document.getElementById('email');
let cpfInput = document.getElementById('cpf');
let escolaridadeSelect = document.getElementById('escolaridade');
let telefoneInput = document.getElementById('telefone');
let enderecoInput = document.getElementById('endereco');
let generoSelect = document.getElementById('genero');
let dataDeNascimentoInput = document.getElementById('dataDeNascimento');
let excluirBtns = document.querySelectorAll('.gridAcaoSubExcluir');
let editarBtns = document.querySelectorAll('.gridAcaoSubEditar');
let form = document.getElementById('form');
let enviarOuEditar = 'enviar';

let quantidadeDeFormulariosNaTabela = 0;



let quantidadeDeContatoss = 1;
form.addEventListener("submit", function (e) {
    e.preventDefault();

    const formData = new FormData(form);
    let formulario;


    async function enviarOuEditarFormularioParaOBackendEReconstruirATabela() {
        formulario = getOsDadosDoFormularioAPartirDoFormData(formData);
        resetarInputsFormulario(form);
        if (enviarOuEditar === 'editar') {
            formulario.id = idFormularioAserEditado;
            console.log(idFormularioAserEditado)
            await editarRegistroFormulario(formulario);
            enviarOuEditar = 'enviar';
        } else {
            await incluirRegistroFormulario(formulario, URL_INCLUIR_REGISTRO_FORMULARIO);
            console.log(JSON.stringify(formulario))

        }
        reconstruirTabela();
    }

    enviarOuEditarFormularioParaOBackendEReconstruirATabela();

});

function reconstruirTabela() {
    limparTabela();
    construirATabela();

}

function getOsDadosDoFormularioAPartirDoFormData(formData) {
    formularioObjData = {
        nome: formData.get('name'),
        email: formData.get('email'),
        cpf: formData.get('cpf'),
        endereco: formData.get('endereco'),
        telefone: formData.get('telefone'),
        escolaridade: formData.get('escolaridade'),
        genero: formData.get('genero'),
        dataDeNascimento: formData.get('dataDeNascimento'),
        sizeListaDeContatos: quantidadeDeContatoss.toString(),
        nomeContato1: formData.get('contato-nome1'),
        telefoneContato1: formData.get('contato-telefone1'),
        emailContato1: formData.get('contato-email1')
    }

    for (let i = 2; i <= quantidadeDeContatoss; i++) {
        formularioObjData[`nomeContato${i}`] = formData.get(`contato-nome${quantidadeDeContatoss}`)
        formularioObjData[`telefoneContato${i}`] = formData.get(`contato-telefone${quantidadeDeContatoss}`)
        formularioObjData[`emailContato${i}`] = formData.get(`contato-email${quantidadeDeContatoss}`)
    }


    return formularioObjData;

}

//excluindo/editando um formulario
function adicionarEventListenersNosBotoesDeExcluirEEditar() {
    excluirBtns[excluirBtns.length - 1].addEventListener("click", function (e) {
        async function excluir() {
            await excluirRegistroFormulario(URL_EXCLUIR_REGISTRO_FORMULARIO_BY_ID, e.target.id);
            reconstruirTabela();
        }
        excluir();
    });

    editarBtns[editarBtns.length - 1].addEventListener("click", function (e) {

        copiarOsDadosASeremEditadosParaOsInputs(e.target.id);

        enviarOuEditar = 'editar';
    });
}

function copiarOsDadosASeremEditadosParaOsInputs(idBotao) {
    for (let i = 0; i < registroFormularios.length; i++) {
        if (registroFormularios[i].idFormulario == idBotao) {
            let formularioASerCopiadoParaOsInputs = registroFormularios[i];
            nomeInput.value = formularioASerCopiadoParaOsInputs.nome;
            emailInput.value = formularioASerCopiadoParaOsInputs.email;
            cpfInput.value = formularioASerCopiadoParaOsInputs.cpf;
            enderecoInput.value = formularioASerCopiadoParaOsInputs.endereco;
            telefoneInput.value = formularioASerCopiadoParaOsInputs.telefone;
            escolaridadeSelect.value = formularioASerCopiadoParaOsInputs.escolaridade;
            generoSelect.value = formularioASerCopiadoParaOsInputs.genero;
            dataDeNascimentoInput.value = formularioASerCopiadoParaOsInputs.dataDeNascimento;

            for(let k = 1 ; k <= formularioASerCopiadoParaOsInputs.lista_de_contatos.length ; k++){
                if(k!=1) adicionarContatoBtnn.click();
                let contatoNome = document.getElementById(`contato-nome${k}`);
                let contatoTelefone = document.getElementById(`contato-telefone${k}`);
                let contatoEmail = document.getElementById(`contato-email${k}`);
                contatoNome.value = formularioASerCopiadoParaOsInputs.lista_de_contatos[k-1].nomeContato;
                contatoTelefone.value = formularioASerCopiadoParaOsInputs.lista_de_contatos[k-1].telefoneContato;
                contatoEmail.value = formularioASerCopiadoParaOsInputs.lista_de_contatos[k-1].emailContato;
            }

            idFormularioAserEditado = formularioASerCopiadoParaOsInputs.idFormulario;
            break;
        }
    }
}

//Adicionar Contatos
const adicionarContatoBtnn = document.getElementById('adicionar-contato');
const contato1 = document.getElementById('contato-01');

function criarContato() {
    quantidadeDeContatoss++;
    let contatoAserAdicionado = document.createElement('div');
    contatoAserAdicionado.setAttribute('id', `contato-${quantidadeDeContatoss}`);
    const node = `<p id="${quantidadeDeContatoss}°contato" class="nth-contato"><strong>Contato ${quantidadeDeContatoss}<strong></p>
    <div class="contato-nome-container flexbox">
     <label for="contato-nome${quantidadeDeContatoss}" ><strong>Nome:</strong></label>
     <input type="text" name="contato-nome${quantidadeDeContatoss}" id="contato-nome${quantidadeDeContatoss}" required placeholder="Insira o nome do contato" />
   </div>
    <div class="contato-telefone-container flexbox">
   <label for="contato-telefone" ><strong>Telefone:</strong></label>
     <input type="tel" name="contato-telefone${quantidadeDeContatoss}" id="contato-telefone${quantidadeDeContatoss}" required placeholder="(xx)xxxxx-xxxx"/>
   </div>
    <div class="contato-email-container flexbox">
     <label for="contato-email" ><strong>E-mail:</strong></label>

     <input type="email" name="contato-email${quantidadeDeContatoss}" id="contato-email${quantidadeDeContatoss}" required placeholder="Insira o email do contato" />
   </div>
    `;
    contatoAserAdicionado.innerHTML = node;

    return contatoAserAdicionado;
}

adicionarContatoBtnn.addEventListener("click", function () {
    document.getElementById("div-referencia-pra-add-contatos").before(criarContato());
});

//removerContatos
const removerContatoBtn = document.getElementById("remove-contato");

function removerContato() {
    if (quantidadeDeContatoss > 1) {
        const contatoAserRemovido = document.getElementById(
            `contato-${quantidadeDeContatoss}`
        );
        contatoAserRemovido.parentNode.removeChild(contatoAserRemovido);
        quantidadeDeContatoss--;
    }
}

removerContatoBtn.addEventListener("click", function () {
    removerContato();
})

function limparTabela() {
    for (let i = quantidadeDeFormulariosNaTabela; i > 0; i--) {
        let removerDaTabela = document.querySelectorAll(`.referencia${i}`);
        removerDaTabela[0].remove();
        removerDaTabela[1].remove();
        removerDaTabela[2].remove();
        removerDaTabela[3].remove();
    }
    quantidadeDeFormulariosNaTabela = 0;
}


function adicionarDadosNaTabela(formularios) {

    for (let i = 0; i < formularios.length; i++) {
        let nomeAserAdicionado = document.createElement('div');
        let emailAserAdicionado = document.createElement('div');
        let escolaridadeAserAdicionado = document.createElement('div');
        let botoesAserAdicionado = document.createElement('div');

        nomeAserAdicionado.setAttribute('class', `referencia${i + 1}`);
        nomeAserAdicionado.textContent = formularios[i].nome;


        emailAserAdicionado.setAttribute('class', `referencia${i + 1}`);
        emailAserAdicionado.textContent = formularios[i].email;

        escolaridadeAserAdicionado.setAttribute('class', `referencia${i + 1}`);
        escolaridadeAserAdicionado.textContent = formularios[i].escolaridade;

        botoesAserAdicionado.setAttribute('class', `referencia${i + 1}`);
        botoesAserAdicionado.innerHTML = `
        <button class="gridAcaoSubEditar" id="${formularios[i].idFormulario}">Editar</button>
        <button class="gridAcaoSubExcluir" id="${formularios[i].idFormulario}">Excluir</button>
        <br><br>
    `;
        let divReferenciaInsercao = document.querySelector('.gridRefAdicionar');

        divReferenciaInsercao.before(nomeAserAdicionado);
        divReferenciaInsercao.before(emailAserAdicionado);
        divReferenciaInsercao.before(escolaridadeAserAdicionado);
        divReferenciaInsercao.before(botoesAserAdicionado);
        quantidadeDeFormulariosNaTabela++;
        excluirBtns = document.querySelectorAll('.gridAcaoSubExcluir');
        editarBtns = document.querySelectorAll('.gridAcaoSubEditar');

        adicionarEventListenersNosBotoesDeExcluirEEditar()
    }
}


function resetarInputsFormulario(form) {
    form.reset();
    if (quantidadeDeContatoss > 1) {
        for (let i = 2; i <= quantidadeDeContatoss; i++) {
            let removerContato = document.getElementById(`contato-${i}`);
            removerContato.remove();
        }
        quantidadeDeContatoss = 1;
    }
}








