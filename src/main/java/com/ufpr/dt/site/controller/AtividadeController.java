package com.ufpr.dt.site.controller;

import com.ufpr.dt.site.dto.AtividadeLista;
import com.ufpr.dt.site.dto.AtividadeRelatorio;
import com.ufpr.dt.site.dto.Frase;
import com.ufpr.dt.site.dto.NovaLista;
import com.ufpr.dt.site.entity.Atividade;
import com.ufpr.dt.site.entity.Pessoa;
import com.ufpr.dt.site.repository.AtividadeRepository;
import com.ufpr.dt.site.repository.PessoaRepository;
import com.ufpr.dt.site.repository.TipoAtividadeRepository;
import com.ufpr.dt.site.service.AtividadeService;
import com.ufpr.dt.site.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequestMapping(value = "/atividade")
public class AtividadeController {

    @Autowired
    AtividadeRepository atividadeRepository;

    @Autowired
    TipoAtividadeRepository tipoAtividadeRepository;

    @Autowired
    ListaService listaService;

    @Autowired
    AtividadeService atividadeService;

    @Autowired
    PessoaRepository pessoaRepository;

    @PersistenceContext
    protected EntityManager em;

    @GetMapping("/{id}/revisao/")
    public ModelAndView startRevisao(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Pessoa p = pessoaRepository.findByEmail(authentication.getName());
        Atividade atividade = new Atividade(tipoAtividadeRepository.findOne(Long.parseLong("1")), listaService.findById(id), p);
        int min, max;
        min = 10000;
        max = 99999;
        do {
            Long randomNum = ThreadLocalRandom.current().nextLong(min, max + 1);
            atividade.setPin(randomNum);
            if (repetido(atividade)) {
                System.out.println("A Atividade with pin " + atividade.getPin() + " already exist");
            }
        } while (repetido(atividade));

        atividade = atividadeRepository.save(atividade);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("\n create " + atividade.getPin().toString() + "\n");
        atividadeService.create(atividade);
        System.out.printf("\n " + atividade.getPin().toString() + "\n");
        ModelAndView mv = new ModelAndView("/vo_atividade");
        String nome = atividade.getLista().getNome() + " - " + atividade.getTipoAtividade().getAtividade() + " - " + " Turma " + atividade.getLista().getTurma();
        mv.addObject("nome", nome);
        mv.addObject("pin", atividade.getPin().toString());
        mv.addObject("pessoas", atividade.getPessoas());
        mv.addObject("estado", atividade.getEstado());
        return mv;
    }

    @GetMapping("/{id}/traducao/")
    public ModelAndView startTraducao(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Pessoa p = pessoaRepository.findByEmail(authentication.getName());
        Atividade atividade = new Atividade(tipoAtividadeRepository.findOne(Long.parseLong("2")), listaService.findById(id), p);
        int min, max;
        min = 10000;
        max = 99999;
        do {
            Long randomNum = ThreadLocalRandom.current().nextLong(min, max + 1);
            atividade.setPin(randomNum);
            if (repetido(atividade)) {
                System.out.println("A Atividade with pin " + atividade.getPin() + " already exist");
            }
        } while (repetido(atividade));

        atividadeRepository.save(atividade);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("\n create " + atividade.getPin().toString() + "\n");
        atividadeService.create(atividade);
        System.out.printf("\n " + atividade.getPin().toString() + "\n");
        System.out.printf("\n " + atividade.getPin().toString() + "\n");
        ModelAndView mv = new ModelAndView("/vo_atividade");
        String nome = atividade.getLista().getNome() + " - " + atividade.getTipoAtividade().getAtividade() + " - " + " Turma " + atividade.getLista().getTurma();
        mv.addObject("nome", nome);
        mv.addObject("pin", atividade.getPin().toString());
        mv.addObject("pessoas", atividade.getPessoas());
        mv.addObject("estado", atividade.getEstado());
        return mv;
    }

    public boolean repetido(Atividade atividade) {
        Atividade a = findByPin(atividade.getPin());
        if (a != null) {
            return true;
        } else {
            return false;
        }
    }

    public Atividade findByPin(Long pin) {
        em.clear();
        return atividadeRepository.findByPin(pin);
    }

    @GetMapping("/pin/{pin}/")
    public ModelAndView pinCarregar(@PathVariable("pin") Long pin) {
        ModelAndView mv = new ModelAndView("/vo_atividade");
        Atividade atividade = atividadeRepository.findByPin(pin);
        String nome = atividade.getLista().getNome() + " - " + atividade.getTipoAtividade().getAtividade() + " - " + " Turma " + atividade.getLista().getTurma();
        mv.addObject("nome", nome);
        mv.addObject("pin", atividade.getPin().toString());
        mv.addObject("pessoas", atividade.getPessoas());
        mv.addObject("estado", atividade.getEstado());
        return mv;
    }
    @GetMapping("/{pin}/iniciar")
    public ModelAndView pinIniciar(@PathVariable("pin") Long pin) {
        ModelAndView mv = new ModelAndView("/vo_atividade");
        Atividade atividade = atividadeRepository.findByPin(pin);
        atividadeService.start(atividade);
        String nome = atividade.getLista().getNome() + " - " + atividade.getTipoAtividade().getAtividade() + " - " + " Turma " + atividade.getLista().getTurma();
        mv.addObject("nome", nome);
        mv.addObject("pin", atividade.getPin().toString());
        mv.addObject("pessoas", atividade.getPessoas());
        mv.addObject("estado", atividade.getEstado());
        return mv;
    }

    @GetMapping("/minhas")
    public ModelAndView mostrarMinhas(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Pessoa p = pessoaRepository.findByEmail(authentication.getName());
        ModelAndView modelAndView = new ModelAndView("/vo_atividades");
        List<AtividadeLista> atividadesLista = atividadeService.atividadeLista(p);
        modelAndView.addObject("atividades", atividadesLista);
        return modelAndView;
    }

    @GetMapping("/{pin}/relatorio")
    public ModelAndView mostrarRelatorio(@PathVariable("pin") Long pin){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Pessoa p = pessoaRepository.findByEmail(authentication.getName());
        Atividade atividade = atividadeRepository.findByPin(pin);
        ModelAndView modelAndView = new ModelAndView("/vo_relatorio");
        List<AtividadeRelatorio> atividadeRelatorios = atividadeService.atividadeRelatorios(atividade);
        modelAndView.addObject("atividades", atividadeRelatorios);
        return modelAndView;
    }
}
