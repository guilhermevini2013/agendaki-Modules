
export interface PaymentInfo {
  paymentId: string;
  price: number;
  cpf: string;
  paymentStatus: 'WAITING' | 'PAID' | 'FAILED'; // Ajuste conforme os status possíveis
  typeSignature: 'ANNUAL' | 'MONTHLY'; // Ajuste conforme os tipos possíveis
  typePayment: 'PIX' | 'CREDIT_CARD' | 'DEBIT_CARD'; // Ajuste conforme os tipos possíveis
  dateOpen: string;
  urlPix: string;
  urlImagePix: string;
  expireTime: string;
  signatureSpecification: SignatureSpecification;
}
interface  SignatureSpecification {
  date: string;
  price: number;
  description: string;
}
