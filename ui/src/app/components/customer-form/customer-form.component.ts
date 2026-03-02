import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';
import { Router } from '@angular/router';
import { CustomerData } from '../../models/customer.model';
import { ToastType } from '../../models/toast-type.enum';
import { ApiResponse } from '../../models/api-response.model';

@Component({
  selector: 'app-customer-form',
  imports: [ReactiveFormsModule],
  templateUrl: './customer-form.component.html',
  styleUrl: './customer-form.component.css'
})
export class CustomerFormComponent {
  FORM_TITLES = {
    create: 'Registro de cliente',
    edit: 'Editar la información del cliente'
  };

  FORM_DESCRIPTIONS = {
    create: 'Registra la información de un cliente nuevo.',
    edit: 'Modifica la información de un cliente.'
  };

  GENERIC_ERROR = 'Ha ocurrido un error desconocido';

  formTitle: string = "";
  formDescription: string = "";
  showToast: boolean = false;
  toastType: string = '';
  toastDescription: string = '';
  private toastTimeout: any;

  isLoading = false;

  customer: CustomerData = {
    documentNumber: 0,
    name: '',
    email: '',
    active: false
  };

  isEdit: boolean = false;

  form!: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private readonly customerService: CustomerService,
    private readonly router: Router,
  ) { }

  ngOnInit() {
    this.customer.documentNumber = Number(this.route.snapshot.paramMap.get('id'));
    this.isEdit = this.customer.documentNumber != 0;

    this.form = new FormGroup({
      customerID: new FormControl({ value: '', disabled: this.isEdit }),
      customerName: new FormControl(''),
      customerEmail: new FormControl('')
    });

    if (this.isEdit) {
      this.formTitle = this.FORM_TITLES.edit;
      this.formDescription = this.FORM_DESCRIPTIONS.edit;

      this.loadEdit();
    } else {
      this.formTitle = this.FORM_TITLES.create;
      this.formDescription = this.FORM_DESCRIPTIONS.create;
    }
  }

  sendForm() {
    if (this.isEdit) {
      this.updateCustomer();
    } else {
      this.saveCustomer();
    }
  }

  loadEdit() {
    this.isLoading = true;
    this.customerService.getCustomer(this.customer.documentNumber).subscribe({
      next: response => this.handleApiResponse(response, data => {
        const { documentNumber, name, email } = data;

        this.form.patchValue({
          customerID: documentNumber ?? '',
          customerName: name ?? '',
          customerEmail: email ?? ''
        });

        this.customer.active = data.active;
      }),
      error: () => this.displayToast(this.GENERIC_ERROR, ToastType.Danger),
      complete: () => this.isLoading = false
    });


  }

  saveCustomer() {
    this.isLoading = true;
    this.customer.documentNumber = Number(this.form?.get('customerID')?.value ?? 0);
    this.customer.name = this.form?.get('customerName')?.value ?? '';
    this.customer.email = this.form?.get('customerEmail')?.value ?? '';

    this.customerService.createCustomer(this.customer).subscribe({
      next: response => this.handleApiResponse(response, () => {
        this.router.navigate(['/']);
      }),
      error: () => this.displayToast(this.GENERIC_ERROR, ToastType.Danger),
      complete: () => this.isLoading = false
    });
  }

  updateCustomer() {
    this.isLoading = true;
    this.customer.documentNumber = this.customer.documentNumber;
    this.customer.name = this.form?.get('customerName')?.value ?? '';
    this.customer.email = this.form?.get('customerEmail')?.value ?? '';

    this.customerService.updateCustomer(this.customer).subscribe({
      next: (response => this.handleApiResponse(response, () => {
        this.router.navigate(['/']);
      })),
      error: (() => {
        this.displayToast(this.GENERIC_ERROR, ToastType.Danger);
      }),
      complete: () => this.isLoading = false
    });
  }

  updateStatus() {
    this.isLoading = true;
    this.customerService.updateCustomerStatus(this.customer.documentNumber).subscribe({
      next: (response => this.handleApiResponse(response, data => {
        this.customer.active = data.active;
        this.displayToast(response.status.message, ToastType.Primary);
      })),
      error: (() => {
        this.displayToast(this.GENERIC_ERROR, ToastType.Danger);
      }),
      complete: () => this.isLoading = false
    });
  }

  handleApiResponse<T>(
    response: ApiResponse<T>,
    onSuccess: (data: T) => void
  ) {
    switch (response.status.statusCode) {
      case 200:
        onSuccess(response.responseData);
        break;
      case 404:
        this.displayToast(response.status.message, ToastType.Warning);
        break;
      default:
        this.displayToast(response.status.message, ToastType.Danger);
    }
  }

  displayToast(toastDescription: string, toastType: string) {
    this.showToast = false;

    this.toastDescription = toastDescription;
    this.toastType = toastType;
    this.showToast = true;

    if (this.toastTimeout) {
      clearTimeout(this.toastTimeout);
    }

    this.toastTimeout = setTimeout(() => {
      this.showToast = false;
      this.toastTimeout = null;
    }, 5000);
  }

}
