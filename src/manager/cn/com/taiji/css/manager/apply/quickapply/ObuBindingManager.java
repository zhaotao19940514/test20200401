package cn.com.taiji.css.manager.apply.quickapply;

import javax.validation.Valid;

import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.customerservice.card.AppCardStatusChangeResponse;
import cn.com.taiji.css.model.apply.quickapply.CardObuBindingRequest;

public interface ObuBindingManager {

	public AppCardStatusChangeResponse cardObuBinding(@Valid CardObuBindingRequest appReq, User user);
}
