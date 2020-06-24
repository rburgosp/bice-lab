import React, { Component } from 'react';
import CreatableSelect from 'react-select/creatable'
import {
  Button
} from "reactstrap";

class Commodity extends Component {


  state = {
    persona: [],
    commoditySelect: [],
    rutPagoMantisa: 0,
    valorUsdolar: 0,
    form: {
      sumaCommodity: 0
    },

  };

  componentDidMount() {
    this.cargarSelectPersona();
    this.cargarSelectCommodity();
  }
  cargarSelectPersona = () => {
    //Modificar segun direccion y puerto donde se escuentre el servicio
    const url = `http://localhost:8090/MsRestGestionUsuario/service/v1/findUserAll`;
    fetch(url)
      .then(respuesta => respuesta.json())
      .then(resultado => { console.log(resultado); this.setState({ persona: resultado }) })
      .catch((err) => {
        console.error(err);
        alert('Error Llamada Servicio ' + err)
      });
  }





  cargarSelectCommodity = () => {
    //Modificar segun direccion y puerto donde se escuentre el servicio
    const url = `http://localhost:8091/MsRestProxyIndicesEconomicos/service/v1/findCommodotyCompra`;
    fetch(url)
      .then(respuesta => respuesta.json())
      .then(resultado => { console.log(resultado); this.setState({ commoditySelect: resultado }) })
      .catch((err) => {
        console.error(err);
        alert('Error Llamada Servicio ' + err)
      });
  }

  handleChangeCommodity = (newValue, actionMeta) => {
    let suma = 0

    console.group('Value Changed');
    console.log(newValue);
    console.log(`action: ${actionMeta.action}`);
    if (newValue) {
      var i;
      for (i = 0; i < newValue.length; i++) {

        console.log('->', newValue[i].value)
        suma = parseFloat(newValue[i].value) + parseFloat(suma)
        this.setState({ form: { sumaCommodity: suma } })
        console.log('suma', suma)
      }
    } else {
      this.setState({ form: { sumaCommodity: 0 } })
    }
    this.obtenerValorUsd()
    console.groupEnd();
  };

  handleChangePersona = (newValue, actionMeta) => {
    console.group('Value Changed');
    this.setState({ rutPagoMantisa: newValue })
    console.log();
    console.log(`action: ${actionMeta.action}`);
    console.groupEnd();
  };



  montoPagoUsd = React.createRef();
  montoPagoClp = React.createRef();

  pagarClp = (e) => {
    console.log('Pagar..',)

    e.preventDefault();
    let validacionPago = true;
    if (this.state.rutPagoMantisa < 1) {
      validacionPago = false;
    }

    if (validacionPago === true) {
      const rutMantisa = this.state.rutPagoMantisa.rutMantisa;
      const pagoUsd = this.montoPagoUsd.current.value;
      const pagoClp = this.montoPagoClp.current.value;
      console.log(rutMantisa)
      console.log(pagoUsd)
      console.log(pagoClp)
      //Modificar segun direccion y puerto donde se escuentre el servicio
      const urlCreateUser = `http://localhost:8090/MsRestGestionUsuario/service/v1/saveBuyCommodity`;
      fetch(urlCreateUser
        , {
          method: 'POST',
          body: JSON.stringify({
            "rutMantisa": `${rutMantisa}`,
            "totalCommodityUsd": `${pagoUsd}`,
            "totalCommodityClp": `${pagoClp}`
          }),
          headers: {
            'Content-Type': 'application/json'
          }
        })
        .then(res => res.json())
        .then((out) => {
          console.log('Output: ', out)
          /*   if(out.respuestaTransaccion){
                 alert('Exito Crud '+out.mensajeTransaccion)
              
             }else{
                 alert('Error Crud '+out.mensajeTransaccion)
                
             }*/
        }).catch((err) => {
          console.error(err);
          alert('Error Llamada Servicio ' + err)

        });
      window.location.reload(false);
    } else {
      alert("No ha seleccionado Persona")
    }
  }

  obtenerValorUsd = () => {
    //Modificar segun direccion y puerto donde se escuentre el servicio
    const url = `http://localhost:8091/MsRestProxyIndicesEconomicos/service/v1/findCommodoty/dolar`;
    fetch(url)
      .then(respuesta => respuesta.json())
      .then(resultado => { console.log(resultado); this.setState({ valorUsdolar: resultado.value }) })
      .catch((err) => {
        console.error(err);
        alert('Error Llamada Servicio ' + err)
      });
  }

  render() {

    return (

      <table>
        <tbody>
          <tr>
            <td>
              <div style={{ width: '400px', padding: '20px' }}>
                <CreatableSelect
                  isClearable
                  getOptionLabel={(option) => option.nombre}
                  getOptionValue={(option) => option.rutMantisa}
                  options={this.state.persona}
                  onMenuOpen={this.cargarSelectPersona}
                  onChange={this.handleChangePersona}

                />
              </div>
            </td>
            <td>
              <div style={{ width: '400px', padding: '20px' }}>
                <CreatableSelect
                  isMulti
                  getOptionLabel={(option) => option.key}
                  getOptionValue={(option) => option.value}
                  onChange={this.handleChangeCommodity}
                  options={this.state.commoditySelect}
                />
              </div>
            </td>
          </tr>
          <tr>
            <td>
              <div style={{ width: '400px', padding: '20px' }} className="form-group">
                <label>USD</label>
                <input ref={this.montoPagoUsd}
                  className="form-suma"
                  readOnly
                  name="sumCommodity"
                  type="text"
                  value={this.state.form.sumaCommodity}
                />
              </div>
            </td>
            <td>
              <div style={{ width: '400px', padding: '20px' }} className="form-group">
                <label>Total a Pagar en CLP</label>
                <input ref={this.montoPagoClp}
                  className="form-pago-clp"
                  readOnly
                  name="pagoClp"
                  type="text"
                  value={Math.round(this.state.form.sumaCommodity * this.state.valorUsdolar)}
                />
              </div>
            </td>
            <td><Button
              color="primary"
              onClick={this.pagarClp}>
              Pagar
            </Button></td>
          </tr>
        </tbody>
      </table>
    );
  }
}

export default Commodity;
