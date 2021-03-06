package lodz.uni.portal.web.controller;

import lodz.uni.portal.form.EventForm;
import lodz.uni.portal.model.Event;
import lodz.uni.portal.service.EachEventService;
import lodz.uni.portal.service.NewEventService;
import lodz.uni.portal.service.NewEventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class NewEventController {
	private static final String NEW_EVENT_PAGE = "newEvent";
	private static final String REDIRECT_EVENT_INFO_PAGE_BASE = "redirect:/eventInfo/";

	private BindingResult result;
	private EventForm eventForm;

	@Autowired
	NewEventService newEventService;

	@Autowired
	EachEventService eachEventService;

	@RequestMapping(value = { "/createNewEvent" }, method = RequestMethod.GET)
	public String showFormForNewEvent(Model model) {

		model.addAttribute("eventForm", new EventForm());
		model.addAttribute("sportNames", newEventService.getSportNames());
		model.addAttribute("loggedInUserName", eachEventService.getLoggedInUser().getNickname());
		return NEW_EVENT_PAGE;
	}

	@RequestMapping(value = { "/createNewEvent" }, method = RequestMethod.POST)
	public String processForm(@Valid @ModelAttribute("eventForm") EventForm form,
							   BindingResult result,
							   Model model, RedirectAttributes redirectModel) {
		this.result = result;
		this.eventForm = form;

		if (this.result.hasErrors()) {
			model.addAttribute("sportNames", newEventService.getSportNames());
			model.addAttribute("loggedInUserName", eachEventService.getLoggedInUser().getNickname());
			return NEW_EVENT_PAGE;
		}
		//validateOtherFiled();

		Event event = getFilledEvent();
		persistNewEvent(event);
		redirectModel.addFlashAttribute("event", event);
		return REDIRECT_EVENT_INFO_PAGE_BASE + event.getId();
	}

	private void validateOtherFiled() {
		newEventService.validateOtherFiled(eventForm, result);
	}

	private void persistNewEvent(Event event) {
		newEventService.persistNewEvent(event);
	}

	private Event getFilledEvent() {
		return newEventService.createAndFillNewEvent(eventForm);
	}
}
