package org.example.integration;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.context.MuleContextFactory;
import org.mule.config.spring.SpringXmlConfigurationBuilder;
import org.mule.context.DefaultMuleContextFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MuleApplicationContext implements ApplicationContextAware
{
  private String[]    configurationFiles;
  private MuleContext muleContext;

  public MuleApplicationContext(final String[] configurationFiles)
  {
    this.configurationFiles = configurationFiles;
  }

  @Override
  public void setApplicationContext(final ApplicationContext applicationContext)
  {
    if (muleContext == null)
    {
      try
      {
        start(applicationContext);
      }
      catch (final MuleException e)
      {
        throw new IllegalStateException(e);
      }
    }
  }

  public void shutdown()
  {
    try
    {
      if (muleContext != null)
      {
        muleContext.stop();
      }
    }
    catch (MuleException e)
    {
      e.printStackTrace();
    }
  }

  private void start(final ApplicationContext applicationContext) throws MuleException
  {
    if (muleContext != null)
    {
      return;
    }

    final SpringXmlConfigurationBuilder builder = new SpringXmlConfigurationBuilder(configurationFiles);
    builder.setParentContext(applicationContext);

    final MuleContextFactory muleContextFactory = new DefaultMuleContextFactory();

    muleContext = muleContextFactory.createMuleContext(builder);
    muleContext.start();
  }
}
