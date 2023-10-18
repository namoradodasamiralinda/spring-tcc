const modal = document.querySelector('.modal-container')

const tbody = document.querySelector('tbody')

const sNome = document.querySelector('#m-nome')

const sCargo = document.querySelector('#m-cargo')

const sTelefone = document.querySelector('#m-telefone')

const sEmail = document.querySelector('#m-email')

const sSenha= document.querySelector('#m-senha')

const sCodigo = document.querySelector('#m-codigo')

const btnSalvar = document.querySelector('#btnSalvar')

 

let itens

let id

 

function openModal(edit = false, index = 0) {

  modal.classList.add('active')

 

  modal.onclick = e => {

    if (e.target.className.indexOf('modal-container') !== -1) {

      modal.classList.remove('active')

    }

  }

 

  if (edit) {

    sNome.value = itens[index].nome

    sCargo.value = itens[index].cargo

    sTelefone.value = itens[index].telefone

    sEmail.value = itens[index].email

    sSenha.value = itens[index].senha

    sCodigo.value = itens[index].codigo

    id = index

  } else {

    sNome.value = ''

    sCargo.value = ''

    sTelefone.value = ''

    sEmail.value = ''

    sSenha.value = ''

    sCodigo.value = ''

  }

  

}

function openModal_2(edit = false, index = 0) {

  modal.classList.add('active')

 

  modal.onclick = e => {

    if (e.target.className.indexOf('modal-container_2') !== -1) {

      modal.classList.remove('active')

    }

  }

 

  if (edit) {

    sNome.value = itens[index].nome

    sCargo.value = itens[index].cargo

    sTelefone.value = itens[index].telefone

    sEmail.value = itens[index].email

    sSenha.value = itens[index].senha

    sCodigo.value = itens[index].codigo

    id = index

  } else {

    sNome.value = ''

    sCargo.value = ''

    sTelefone.value = ''

    sEmail.value = ''

    sSenha.value = ''

    sCodigo.value = ''

  }

  

}

 

function editItem(index) {

 

  openModal(true, index)

}

 

function deleteItem(index) {

  itens.splice(index, 1)

  setItensBD()

  loadItens()

}

 

function insertItem(item, index) {

  let tr = document.createElement('tr')

 

  tr.innerHTML = `

    <td>${item.nome}</td>

    <td>${item.cargo}</td>

    <td>${item.telefone}</td>

    <td>${item.email}</td>

    <td>${item.senha}</td>

    <td>${item.codigo}</td>

 

    <td class="acao">

      <button onclick="editItem(${index})"><i class='bx bx-edit' ></i></button>

    </td>

    <td class="acao">

      <button onclick="deleteItem(${index})"><i class='bx bx-trash'></i></button>

    </td>

  `

  tbody.appendChild(tr)

}

 

btnSalvar.onclick = e => {

  

  if (sNome.value == '' || sCargo.value == '' || sTelefone.value == ''|| sEmail.value == ''|| sSenha.value == ''|| sCodigo.value == '') {

    return

  }

 

  e.preventDefault();

 

  if (id !== undefined) {

    itens[id].nome = sNome.value

    itens[id].cargo = sCargo.value

    itens[id].telefone = sTelefone.value

    itens[id].email = sEmail.value

    itens[id].senha = sSenha.value

    itens[id].codigo = sCodigo.value

  } else {

    itens.push({'nome': sNome.value, 'cargo': sCargo.value, 'telefone': sTelefone.value, 'senha': sSenha.value,'email': sEmail.value, 'codigo': sCodigo.value,})

  }

 

  setItensBD()

 

  modal.classList.remove('active')

  loadItens()

  id = undefined

}

 

function loadItens() {

  itens = getItensBD()

  tbody.innerHTML = ''

  itens.forEach((item, index) => {

    insertItem(item, index)

  })

 

}

 

const getItensBD = () => JSON.parse(localStorage.getItem('dbfunc')) ?? []

const setItensBD = () => localStorage.setItem('dbfunc', JSON.stringify(itens))

 

loadItens()