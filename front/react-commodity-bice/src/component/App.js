import React, { Component } from 'react';
import "../css/App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import {
  Table,
  Button,
  Container,
  Modal,
  ModalHeader,
  ModalBody,
  FormGroup,
  ModalFooter,
} from "reactstrap";
import Commodity from "./Commodity"


class App extends Component {


  componentDidMount() {
    this.cargarTabla()
  }
  state = {
    data: [],
    modalActualizar: false,
    modalInsertar: false,
    form: {
      rutMantisa: "",
      rutDv: "",
      nombre: "",
    },
  };

  mostrarModalActualizar = (dato) => {
    this.setState({
      form: dato,
      modalActualizar: true,
    });
  };

  cerrarModalActualizar = () => {
    this.setState({ modalActualizar: false });
  };

  mostrarModalInsertar = () => {
    this.setState({
      modalInsertar: true,
    });
  };

  cerrarModalInsertar = () => {
    this.setState({ modalInsertar: false });
  };

  editar = (dato) => {
    console.log(dato.nombre)
    //Modificar segun direccion y puerto donde se escuentre el servicio
    const urlCreateUser = `http://localhost:8090/MsRestGestionUsuario/service/v1/updateUser/${dato.rutMantisa}`;
    fetch(urlCreateUser
      , {
        method: 'PATCH',
        body: JSON.stringify({
          "nombre": `${dato.nombre}`,
          "apellido": `${dato.apellido}`,
          "email": `${dato.email}`

        }),
        headers: {
          'Content-Type': 'application/json'
        }
      })
      .then(res => res.json())
      .then((out) => {
        console.log('Output: ', out)
        this.cargarTabla();
        this.setState({ modalActualizar: false });
        /* if(out.respuestaTransaccion){
             alert('Exito Crud '+out.mensajeTransaccion)
             this.setState({ modalInsertar: false});
         }else{
             alert('Error Crud '+out.mensajeTransaccion)
             this.setState({ modalInsertar: false});
         }*/
      }).catch((err) => {
        console.error(err);
        alert('Error Llamada Servicio ' + err)
        this.cargarTabla();
        this.setState({ modalActualizar: false });
      });



  };

  eliminar = (dato) => {
    console.log(dato)
    var opcion = window.confirm(`¿Estás Seguro que deseas Eliminar el Rut ${dato.rutMantisa} ?`);
    if (opcion === true) {
      //Modificar segun direccion y puerto donde se escuentre el servicio     
      const urlCreateUser = `http://localhost:8090/MsRestGestionUsuario//service/v1/deleteUser/${dato.rutMantisa}`;
      fetch(urlCreateUser
        , {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json'
          }
        })
        .then(res => res.json())
        .then((out) => {
          console.log('Output: ', out)
          this.setState({ modalActualizar: false });
          this.cargarTabla();
          /* if(out.respuestaTransaccion){
               alert('Exito Crud '+out.mensajeTransaccion)
               this.setState({ modalInsertar: false});
           }else{
               alert('Error Crud '+out.mensajeTransaccion)
               this.setState({ modalInsertar: false});
           }*/
        }).catch((err) => {
          console.error(err);
          alert('Error Llamada Servicio ' + err)
          this.setState({ modalActualizar: false });
          this.cargarTabla();
        });

    }



    //}
  };


  handleChange = (e) => {
    this.setState({
      form: {
        ...this.state.form,
        [e.target.name]: e.target.value,
      },
    });
  };

  frmRutMantisa = React.createRef();
  frmRutDv = React.createRef();
  frmNombre = React.createRef();
  frmApellido = React.createRef();
  frmEmail = React.createRef();


  insertarPersona = (e) => {
    e.preventDefault();
    const rutMantisa = this.frmRutMantisa.current.value;
    const rutDv = this.frmRutDv.current.value;
    const nombre = this.frmNombre.current.value;
    const apellido = this.frmApellido.current.value;
    const email = this.frmEmail.current.value;
    let validacionCrear= true;


    if( this.validateEmail(email)===false){
        validacionCrear=false;
        alert('Formato de Email Erroneo.')
    }
    if( rutMantisa ==null ){
      validacionCrear=false;
    } if( rutDv==null){
      validacionCrear=false;
    } if(nombre ==null){
      validacionCrear=false;
    } if( apellido==null){
      validacionCrear=false;
    }

    if(validacionCrear===true){
    //Modificar segun direccion y puerto donde se escuentre el servicio
    const urlCreateUser = `http://localhost:8090/MsRestGestionUsuario/service/v1/createUser`;
    fetch(urlCreateUser
      , {
        method: 'POST',
        body: JSON.stringify({
          "rutMantisa": `${rutMantisa}`,
          "rutDv": `${rutDv}`,
          "nombre": `${nombre}`,
          "apellido": `${apellido}`,
          "email": `${email}`,

        }),
        headers: {
          'Content-Type': 'application/json'
        }
      })
      .then(res => res.json())
      .then((out) => {
        console.log('Output: ', out)
        this.setState({ modalInsertar: false });
        this.cargarTabla();
        /*  if(out.respuestaTransaccion){
              alert('Exito Crud '+out.mensajeTransaccion)
              
          }else{
              alert('Error Crud '+out.mensajeTransaccion)
             
          }*/
      }).catch((err) => {
        console.error(err);
        alert('Error Llamada Servicio ' + err)
        this.setState({ modalInsertar: false });
        this.cargarTabla();
      });
    }else{
      alert('Debe Completar todos los campos')
    }
  }

  cargarTabla() {
    //Modificar segun direccion y puerto donde se escuentre el servicio
    const url = `http://localhost:8090/MsRestGestionUsuario/service/v1/findUserAll`;
    console.log(url)
    fetch(url)
      .then(respuesta => respuesta.json())
      .then(resultado => { console.log(resultado); this.setState({ data: resultado }) })
      .catch((err) => {
        console.error(err);
        alert('Error Llamada Servicio ' + err)
      });
  }

  validateEmail = (emailField) => {
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

    if (reg.test(emailField) === false) 
    {       
     
        return false;
    }

    return true;

}

  render() {

    return (
      <>
        <Container>
          <br />
          <Button color="success" onClick={() => this.mostrarModalInsertar()}>Crear</Button>
          <br />
          <br />
          <Table>
            <thead>
              <tr>
                <th>Rut</th>
                <th>Dv</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Email</th>
                <th>Compra Comm. USD</th>
                <th>Compra Comm. CLP</th>
              </tr>
            </thead>

            <tbody>
              {this.state.data.map((dato) => (
                <tr key={dato.rutMantisa}>
                  <td>{dato.rutMantisa}</td>
                  <td>{dato.rutDv}</td>
                  <td>{dato.nombre}</td>
                  <td>{dato.apellido}</td>
                  <td>{dato.email}</td>
                  <td>{dato.totalCommodityUsd}</td>
                  <td>{dato.totalCommodityClp}</td>
                  <td>
                    <Button
                      color="primary"
                      onClick={() => this.mostrarModalActualizar(dato)}
                    >
                      Editar
                    </Button>{" "}
                    <Button color="danger" onClick={() => this.eliminar(dato)}>Eliminar</Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Container>

        <Modal isOpen={this.state.modalActualizar}>
          <ModalHeader>
            <div><h3>Editar Persona</h3></div>
          </ModalHeader>

          <ModalBody>
            <FormGroup>
              <label>
                Rut:
              </label>
              <table><tbody><tr><td>
                <input
                  className="form-control"
                  placeholder="Rut"
                  name="rut"
                  maxlength="8"
                  readOnly
                  type="text"
                  value={this.state.form.rutMantisa}
                  style={{ width: '200px', padding: '10px' }}
                /></td><td>
                  <input
                    className="form-control"
                    placeholder="Dv"
                    maxlength="1"
                    readOnly
                    name="dv"
                    type="text"
                    onChange={this.handleChange}
                    value={this.state.form.rutDv}
                    style={{ width: '50px', padding: '10px' }}
                  /></td></tr></tbody>
              </table>
            </FormGroup>


            <FormGroup>
              <label>
                Nombre:
              </label>
              <input
                className="form-control"
                placeholder="Nombre"
                maxlength="20"
                name="nombre"
                type="text"
                onChange={this.handleChange}
                value={this.state.form.nombre}
              />
            </FormGroup>
            <FormGroup>
              <label>
                Apellido:
              </label>
              <input
                className="form-control"
                placeholder="Apellido"
                maxlength="20"
                name="apellido"
                type="text"
                onChange={this.handleChange}
                value={this.state.form.apellido}
              />
              <FormGroup>
                <label>
                  Email:
              </label>
                <input
                  className="form-control"
                  placeholder="Email"
                  maxlength="50"
                  name="email"
                  type="text"
                  onChange={this.handleChange}
                  value={this.state.form.email}
                />

              </FormGroup>
            </FormGroup>
          </ModalBody>

          <ModalFooter>
            <Button
              color="primary"
              onClick={() => this.editar(this.state.form)}
            >
              Editar
            </Button>
            <Button
              color="danger"
              onClick={() => this.cerrarModalActualizar()}
            >
              Cancelar
            </Button>
          </ModalFooter>
        </Modal>



        <Modal isOpen={this.state.modalInsertar}>
          <ModalHeader>
            <div><h3>Insertar Persona</h3></div>
          </ModalHeader>

          <ModalBody>
            <FormGroup>
              <label>
                Rut:
              </label>
              <input ref={this.frmRutMantisa}
                className="form-control"
                placeholder="Rut"  
                maxlength="8"              
                name="rut"
                type="text"
                onChange={this.handleChange}
              // value={this.state.data.length+1}
              />
            </FormGroup>

            <FormGroup>
              <label>
                Dv:
              </label>
              <input ref={this.frmRutDv}
                className="form-control"
                placeholder="Dv"
                maxlength="1"
                name="dv"
                type="text"
                onChange={this.handleChange}
              />
            </FormGroup>

            <FormGroup>
              <label>
                Nombre:
              </label>
              <input ref={this.frmNombre}
                className="form-control"
                placeholder="Nombre"
                maxlength="20"
                name="nombre"
                type="text"
                onChange={this.handleChange}
              />
            </FormGroup>
            <FormGroup>
              <label>
                Apellido:
              </label>
              <input ref={this.frmApellido}
                className="form-control"
                placeholder="Apellido"
                maxlength="20"
                name="apellido"
                type="text"
                onChange={this.handleChange}
              />
            </FormGroup>
            <FormGroup>
              <label>
                Email:
              </label>
              <input ref={this.frmEmail}
                className="form-control"
                placeholder="Email"
                maxlength="50"
                name="email"
                type="text"
                onChange={this.handleChange}
              />
            </FormGroup>
          </ModalBody>

          <ModalFooter>
            <Button
              color="primary"
              onClick={this.insertarPersona}
            >
              Insertar
            </Button>
            <Button
              className="btn btn-danger"
              onClick={() => this.cerrarModalInsertar()}
            >
              Cancelar
            </Button>
          </ModalFooter>
        </Modal>
        <div style={{ width: '400px', padding: '20px' }}>
          <Commodity />
        </div>



      </>

    );
  }
}
export default App;
