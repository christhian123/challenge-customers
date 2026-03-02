import { Routes } from '@angular/router';
import { CustomersListComponent } from './components/customers-list/customers-list.component';
import { CustomerFormComponent } from './components/customer-form/customer-form.component';

export const routes: Routes = [
    { path: 'list', component: CustomersListComponent },
    { path: 'create', component: CustomerFormComponent },
    { path: 'edit/:id', component: CustomerFormComponent },
    { path: '', redirectTo: '/list', pathMatch:  'full'},
    { path: '**', redirectTo: '/list'}
];
