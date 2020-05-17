import './AddVacancyDialog.scss';

import React, {Component} from 'react';
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import Button from "@material-ui/core/Button";
import DialogTitle from "@material-ui/core/DialogTitle";
import Dialog from "@material-ui/core/Dialog";

class AddVacancyDialog extends Component {
  state = {
    data: {
      id: 0,
      name: '',
      description: '',
      salary: 0
    }
  };

  handleAddVacancy (handleAddVacancy, handleClose) {
    handleAddVacancy(this.state.data);
    handleClose();
  };

  render() {
    const {handleClose, handleAddVacancy} = this.props;
    return (
      <Dialog className='dialog' onClose={handleClose} aria-labelledby="customized-dialog-title" open={true}>
        <DialogTitle id="customized-dialog-title" onClose={handleClose}>
          Add vacancy
        </DialogTitle>

        <DialogContent dividers>
          <div className='input-fields-container'>
            <input
              className='input-field form-control'
              type="text"
              onChange={(e) => this.setState({data: {...this.state.data, id: e.target.value}})}
              placeholder="Id"
              style={{width: '200px'}}/>

            <input
              className='input-field form-control'
              type="text"
              onChange={(e) => this.setState({data: {...this.state.data, name: e.target.value}})}
              placeholder="Name"
              style={{width: '200px'}}/>

            <input
              className='input-field form-control'
              type="text"
              onChange={(e) => this.setState({data: {...this.state.data, description: e.target.value}})}
              placeholder="Description"
              style={{width: '200px'}}/>

            <input
              className='input-field form-control'
              type="text"
              onChange={(e) => this.setState({roomData: {...this.state.data, salary: e.target.value}})}
              placeholder="Salary"
              style={{width: '200px'}}/>
          </div>
        </DialogContent>

        <DialogActions>
          <Button autoFocus onClick={() => this.handleAddVacancy(handleAddVacancy, handleClose)} color="primary">
            Add
          </Button>
        </DialogActions>
      </Dialog>)
  }
}

export default AddVacancyDialog;