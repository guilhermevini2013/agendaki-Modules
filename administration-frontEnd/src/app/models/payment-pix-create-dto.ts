import {TypePayment} from "./TypePayment";
import {TypeSignature} from "./TypeSignature";

export interface PaymentPixCreateDTO {
  cpf: string;
  typePayment: TypePayment;
  typeSignature: TypeSignature;
}
