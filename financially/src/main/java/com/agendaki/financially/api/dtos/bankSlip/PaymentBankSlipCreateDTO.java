package com.agendaki.financially.api.dtos.bankSlip;

import com.agendaki.financially.api.dtos.ChargesDTO;
import com.agendaki.financially.api.dtos.ItemsDTO;
import com.agendaki.financially.api.dtos.pix.AmountDTO;
import com.agendaki.financially.api.dtos.pix.CustomerDTO;
import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.models.user.PreUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record PaymentBankSlipCreateDTO(String reference_id, CustomerDTO customer, List<ItemsDTO> items,
                                       List<ChargesDTO> charges) {

    public PaymentBankSlipCreateDTO(PaymentCreateDTO paymentDTO, PreUser preUser) {
        this(preUser.getId(), new CustomerDTO(preUser.getName(), preUser.getUsername(), paymentDTO.cpf().replace(".", "").replace("-", "")),
                List.of(new ItemsDTO(paymentDTO.typeSignature().getDateBySignature().description(), 1, paymentDTO.typeSignature().getDateBySignature().price().toBigInteger().intValue())),
                List.of(new ChargesDTO(preUser.getId(), paymentDTO.typeSignature().getDateBySignature().description(),
                        new AmountDTO(paymentDTO.typeSignature().getDateBySignature().price().toString(), "BRL"),
                        new PaymentMethodDTO("BOLETO",
                                new BankSlipDTO(LocalDate.now().plusDays(3).toString(), Map.of("line_1", "Pagamento processado para DESC Fatura", "line_2", "Via PagSeguro"),
                                        new HolderDTO(preUser.getName(), preUser.getUsername(), paymentDTO.cpf().replace(".", "").replace("-", ""),
                                                paymentDTO.address().get()))))));
    }
}
