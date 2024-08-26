import {TypePayment} from "./TypePayment";
import {TypeSignature} from "./TypeSignature";

export interface Address {
  country: "Brasil";
  region: "Sao Paulo";
  region_code: "SP";
  city: string;
  postal_code: string;
  street: string;
  number: number | unknown;
  locality: "Pinheiros";
}

export interface PaymentBankCreateDTO {
  cpf: string;
  typePayment: TypePayment;
  typeSignature: TypeSignature;
  address: Address;
}
