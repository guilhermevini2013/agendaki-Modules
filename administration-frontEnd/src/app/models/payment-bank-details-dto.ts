import {TypePayment} from "./TypePayment";

export interface PaymentBankDetailsDTO {
  paymentId: string;
  price: number;
  cpf: string;
  typeSignature: 'ANNUAL' | 'QUARTERLY' |'MONTHLY';
  typePayment: TypePayment;
  dateOpen: string;
  description: string;
  due_Date: string;
  linkPDF: string;
  barcode: string;
}
