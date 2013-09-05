package br.com.caelum.caelum.argentum.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.ChartModel;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.CandleFactory;
import br.com.caelum.argentum.Negociacao;
import br.com.caelum.argentum.SerieTemporal;
import br.com.caelum.argentum.ws.ClienteWebService;
import br.com.caelum.caelum.argentum.web.model.GeradorModeloGrafico;
import br.com.caelum.indicadores.IndicadorAbertura;
import br.com.caelum.indicadores.IndicadorFechamento;
import br.com.caelum.indicadores.MediaMovelPonderada;
import br.com.caelum.indicadores.MediaMovelSimples;

@ManagedBean
@SessionScoped
public class ArgentumBean {

	private List<Negociacao> negociacoes;
	private ChartModel modeloGrafico;
	private String titulo;

	public void preparaDados() {
		this.negociacoes = new ClienteWebService().getNegociacoes();
		List<Candle> candles = new CandleFactory()
				.constroiCandles(this.negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);
		GeradorModeloGrafico gerador = new GeradorModeloGrafico(serie, 2,
				serie.getTotal() - 2);

		gerador.plotaIndicador(new MediaMovelSimples(new IndicadorFechamento(),
				3));

		gerador.plotaIndicador(new MediaMovelPonderada(
				new IndicadorFechamento(), 3));

		gerador.plotaIndicador(new MediaMovelSimples(new IndicadorAbertura(), 3));

		gerador.plotaIndicador(new MediaMovelPonderada(new IndicadorAbertura(),
				3));

		gerador.plotaIndicador(new IndicadorAbertura());

		gerador.plotaIndicador(new IndicadorFechamento());

		this.modeloGrafico = gerador.getModeloGrafico();

	}

	public List<Negociacao> getNegociacoes() {
		return negociacoes;
	}

	public ChartModel getModeloGrafico() {
		return modeloGrafico;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
