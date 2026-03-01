import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-customers-list',
  imports: [],
  templateUrl: './customers-list.component.html',
  styleUrl: './customers-list.component.css'
})
export class CustomersListComponent {
  isLoading: boolean = true;
  isError:  boolean =  false;

  customers: any[] = [];

  constructor(
    private readonly router: Router,
    private readonly customerService: CustomerService
  ) {}


  ngOnInit() {
    this.customerService.getCustomersList().subscribe(
      {
        next: (response => {
          if(response.status.statusCode == 200) {
            this.customers = response.responseData;
          } else {
            this.isError = true;
          }
        }),
        error: (() => {
          this.isError = true;
          this.isLoading  = false;
        }),
        complete: () => { this.isLoading  = false; }
      });
  }

  public openCustomerForm(documentNumber?: number) {
    if (documentNumber) {
      this.router.navigate(["edit", documentNumber]);
    } else {
      this.router.navigate(["create"]);
    }
  }
}
