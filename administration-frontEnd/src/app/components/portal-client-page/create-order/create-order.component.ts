import {Component, CUSTOM_ELEMENTS_SCHEMA, inject} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatStepperModule} from '@angular/material/stepper';
import {MatButtonModule} from '@angular/material/button';
import {MatRadioModule} from '@angular/material/radio';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatDividerModule} from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import {CommonModule, DatePipe, NgIf} from '@angular/common';
import {MatListModule} from '@angular/material/list';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {NgxMaskDirective, NgxMaskPipe} from 'ngx-mask';
import {PaymentService} from "../../../services/payment.service";
import {TypePayment} from "../../../models/TypePayment";
import {TypeSignature} from "../../../models/TypeSignature";
import {PaymentPixCreateDTO} from "../../../models/payment-pix-create-dto";
import {NgxSpinnerModule} from "ngx-spinner";
import {PaymentBankCreateDTO} from "../../../models/payment-bank-create-dto";

@Component({
  selector: 'app-create-order',
  standalone: true,
  imports: [
    CommonModule, // Use CommonModule instead of BrowserModule
    MatButtonModule,
    MatStepperModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatRadioModule,
    MatExpansionModule,
    MatListModule,
    MatIconModule,
    MatDividerModule,
    DatePipe,
    MatButtonToggleModule,
    NgIf,
    NgxMaskDirective,
    NgxMaskPipe,
    NgxSpinnerModule, // Include NgxSpinnerModule here
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css'] // Corrected styleUrl to styleUrls
})
export class CreateOrderComponent{
  private _formBuilder = inject(FormBuilder);
  protected pixGenerated:{imagePix:string,urlPix:string,dateExpire:string} = {
    imagePix:"",
    urlPix:"",
    dateExpire:""
  }

  protected bankGenerated: {
    linkPDF: string,
    barcode: string
  } = {
    linkPDF: "",
    barcode: ""
  }

  protected viewPaymentInf:boolean = false;

  firstFormGroup = this._formBuilder.group({
    typeSignature: ['', Validators.required],
  });

  secondFormGroup = this._formBuilder.group({
    typePayment: ['', Validators.required],
  });

  formTo: FormGroup | undefined;

  formGroupBank = this._formBuilder.group({
    cpf: ['', Validators.required],
    street: ['', Validators.required],
    number: ['', Validators.required],
    city: ['', Validators.required],
    cep: ['', Validators.required],
  });

  formGroupPix = this._formBuilder.group({
    cpf: ['', Validators.required],
  });

  constructor(private paymentService: PaymentService) { }

  private getTypeSignature(value: string): TypeSignature {
    switch (value) {
      case '1':
        return TypeSignature.MONTHLY;
      case '2':
        return TypeSignature.QUARTERLY;
      case '3':
        return TypeSignature.ANNUAL;
      default:
        throw new Error('Invalid TypeSignature value');
    }
  }

  private getTypePayment(value: string): TypePayment {
    switch (value) {
      case '1':
        return TypePayment.PIX;
      case '2':
        return TypePayment.BANK_SLIP;
      default:
        throw new Error('Invalid TypePayment value');
    }
  }

  protected createPayment(): void {
    if (this.formGroupPix.valid || this.formGroupBank.valid && this.secondFormGroup.valid && this.formTo?.valid) {
      const typeSignature = this.getTypeSignature(this.firstFormGroup.value.typeSignature!);
      if (this.getTypePayment(this.secondFormGroup.value.typePayment!) == TypePayment.PIX) {
        this.createPaymentPix(typeSignature);
      } else {
        this.createPaymentBank(typeSignature)
      }
    }
  }

  private createPaymentBank(typeSignature: TypeSignature): void {
    const paymentDto: PaymentBankCreateDTO = {
      cpf: this.formGroupBank.value.cpf!.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4'),
      typePayment: TypePayment.BANK_SLIP,
      typeSignature: typeSignature,
      address: {
        city: this.formGroupBank.value.city!,
        number: Number(this.formGroupBank.value.number),
        street: this.formGroupBank.value.street!,
        postal_code: this.formGroupBank.value.cep!,
        country: "Brasil",
        locality: "Pinheiros",
        region: "Sao Paulo",
        region_code: "SP"
      }
    }

    this.paymentService.createPaymentBank(paymentDto).subscribe(
      value => {
        this.bankGenerated = {
          barcode: value.body?.barcode!,
          linkPDF: value.body?.linkPDF!
        }
        this.viewPaymentInf = true;
      },
      error => {
        console.error(error);
      }
    )
  }

  private createPaymentPix(typeSignature: TypeSignature): void {
      const paymentDto: PaymentPixCreateDTO = {
        cpf: this.formGroupPix.value.cpf!.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4'),
        typePayment: TypePayment.PIX,
        typeSignature: typeSignature
      };
    this.paymentService.createPaymentPix(paymentDto).subscribe(
        response => {
          this.pixGenerated = {
            imagePix:response.body?.urlImagePix!,
            urlPix:response.body?.urlPix!,
            dateExpire:response.body?.expireTime!
          }
          this.viewPaymentInf = true;
        },
        error => {
          console.error(error);
        }
      );
    }

  protected checkTypePayment(): string {
    const value = this.secondFormGroup.value?.typePayment;
    if (typeof value === 'string') {
      if (value === '1') {
        this.formTo = this.formGroupPix;
      } else {
        this.formTo = this.formGroupBank;
      }
      return value;
    } else {
      console.log(typeof value);
      return '0';
    }
  }
}

