import {TypePayment} from "./TypePayment";
import {TypeSignature} from "./TypeSignature";

export interface PaymentInfo {
  paymentId: string;
  price: number;
  cpf: string;
  paymentStatus: 'WAITING' | 'PAID' | 'FAILED'; // Ajuste conforme os status possíveis
  typeSignature: TypeSignature;
  typePayment: TypePayment;
  dateOpen: string;
  urlPix: string;
  urlImagePix: string;
  expireTime: string;
  signatureSpecification: SignatureSpecification;
}

interface SignatureSpecification {
  date: string;
  price: number;
  description: string;
}
